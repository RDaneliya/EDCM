package com.edcm.backend.infrastructure.eddn;

import com.edcm.backend.core.properties.ZeromqProperties;
import com.edcm.backend.core.schedule.CommodityCheckService;
import com.edcm.backend.core.schedule.EddbCheckService;
import com.edcm.backend.core.services.ChannelWatcher;
import com.edcm.backend.core.services.commodity.EddnStationCommoditiesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.zeromq.channel.ZeroMqChannel;
import org.springframework.messaging.MessageHandler;
import org.zeromq.ZContext;

@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class ZeromqConfig {

    @Bean
    public ZContext zContext() {
        return new ZContext();
    }

    @Bean(name = "zeroMqChannel")
    public ZeroMqChannel zeroMqPubSubChannel(ZContext context, ZeromqProperties properties) {
        ZeroMqChannel channel = new ZeroMqChannel(context, true);
        channel.setConnectUrl(properties.getEddnChannelUrl());
        return channel;
    }

    @Bean
    @DependsOn(value = {"channelWatcher", "commodityCheckService"})
    @ServiceActivator(inputChannel = "zeroMqChannel")
    public MessageHandler messageService(
            JsonMapper objectMapper,
            EddnStationCommoditiesService eddnStationCommoditiesService,
            ChannelWatcher channelWatcher,
            CommodityCheckService commodityCheckService
    ) {

        commodityCheckService.updateCommodities();

        return new ZeromqMessageHandler(objectMapper, eddnStationCommoditiesService, channelWatcher);
    }
}
