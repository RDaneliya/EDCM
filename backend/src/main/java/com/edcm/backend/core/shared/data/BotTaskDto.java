package com.edcm.backend.core.shared.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class BotTaskDto implements Serializable {
    private Long id;
    private Long commodityId;
    private Long userId;
    private Long price;
}
