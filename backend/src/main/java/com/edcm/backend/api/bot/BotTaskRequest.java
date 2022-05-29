package com.edcm.backend.api.bot;

import com.edcm.backend.core.shared.data.CommodityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BotTaskRequest {
    private Long commodityId;
    private Long userId;
    private Long price;
}
