package com.edcm.backend.core.shared.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDto implements Serializable {
    private Long id;
    private String name;
    private String eddnName;
    private CommodityCategoryDto category;
}
