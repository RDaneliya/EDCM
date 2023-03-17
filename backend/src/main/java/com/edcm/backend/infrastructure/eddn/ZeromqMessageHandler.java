package com.edcm.backend.infrastructure.eddn;

import com.edcm.backend.core.exceptions.NoSchemaException;
import com.edcm.backend.core.services.ChannelWatcher;
import com.edcm.backend.core.services.commodity.EddnStationCommoditiesService;
import com.edcm.backend.core.services.journal.EddnJournalInformationService;
import com.edcm.backend.infrastructure.eddn.schemas.MessageSchema;
import com.edcm.backend.infrastructure.eddn.schemas.commodity.EddnCommodityPayload;
import com.edcm.backend.infrastructure.eddn.schemas.journal.EddnJournalDockedPayload;
import com.edcm.backend.infrastructure.eddn.schemas.journal.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RequiredArgsConstructor
@Slf4j
public class ZeromqMessageHandler implements MessageHandler {
    private final EddnStationCommoditiesService eddnStationCommoditiesService;
    private final EddnJournalInformationService eddnJournalInformationService;
    private final JsonMapper jsonMapper;
    private final ChannelWatcher channelWatcher;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        channelWatcher.resetTimer();

        byte[] output = new byte[256 * 1024];
        byte[] payload = (byte[]) message.getPayload();
        Inflater inflater = new Inflater();
        inflater.setInput(payload);
        try {
            int outputLength = inflater.inflate(output);
            String outputString = new String(output, 0, outputLength, StandardCharsets.UTF_8);
            MessageSchema schemaType = getSchema(outputString);

            if (schemaType == null) {
                return;
            }
            switch (schemaType) {
                case COMMODITY -> processCommodityData(outputString);
                case JOURNAL -> processJournalData(outputString);
            }
        } catch (DataFormatException | JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    private void processCommodityData(String outputString) throws JsonProcessingException {
        EddnCommodityPayload commodityPayload = jsonMapper.readValue(
                outputString,
                EddnCommodityPayload.class
        );

        eddnStationCommoditiesService.saveData(commodityPayload);
    }

    private void processJournalData(String ouputString) throws JsonProcessingException {
        String eventCheck = String.format("\"event\": \"%s\"", Event.DOCKED);
        if (StringUtils.containsIgnoreCase(ouputString, eventCheck)) {
            EddnJournalDockedPayload dockedPayload = jsonMapper
                    .readValue(ouputString, EddnJournalDockedPayload.class);

            eddnJournalInformationService.saveDockedJournalData(dockedPayload);
        }
    }

    private MessageSchema getSchema(String message) {
        try {
            String schemaString = message
                    .replace("{", "")
                    .split(",")[0]
                    .split("\":")[1]
                    .replace("\"", "")
                    .trim();
            return MessageSchema.valueOfSchema(schemaString);
        } catch (NoSchemaException e) {
            log.warn("Seems there is a new schema: ", e);
            return null;
        }
    }
}
