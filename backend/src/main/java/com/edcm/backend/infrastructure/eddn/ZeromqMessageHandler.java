package com.edcm.backend.infrastructure.eddn;

import com.edcm.backend.core.exceptions.NoSchemaException;
import com.edcm.backend.core.services.ChannelWatcher;
import com.edcm.backend.core.services.commodity.EddnStationCommoditiesService;
import com.edcm.backend.infrastructure.eddn.schemas.MessageSchema;
import com.edcm.backend.infrastructure.eddn.schemas.commodity.EddnCommodityPayload;
import com.edcm.backend.infrastructure.eddn.schemas.journal.EddnJournalDockedPayload;
import com.edcm.backend.infrastructure.eddn.schemas.journal.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RequiredArgsConstructor
@Slf4j
public class ZeromqMessageHandler implements MessageHandler {
    private final ObjectMapper objectMapper;
    private final EddnStationCommoditiesService eddnStationCommoditiesService;
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

            if (Objects.requireNonNull(schemaType) == MessageSchema.COMMODITY) {
                processCommodityData(outputString);
            }
        } catch (DataFormatException | JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    private void processCommodityData(String outputString) throws JsonProcessingException {
        EddnCommodityPayload commodityPayload = objectMapper.readValue(
                outputString,
                EddnCommodityPayload.class
        );

        eddnStationCommoditiesService.saveData(commodityPayload);
    }

    private void processJournalData(String ouputString) throws JsonProcessingException {
        if (ouputString.contains(Event.DOCKED.toString())) {
            EddnJournalDockedPayload dockedPayload = objectMapper
                    .readValue(ouputString, EddnJournalDockedPayload.class);

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
