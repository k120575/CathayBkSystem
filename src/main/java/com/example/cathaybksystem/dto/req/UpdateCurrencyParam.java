package com.example.cathaybksystem.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateCurrencyParam {

    @ApiModelProperty(value = "幣別", required = true)
    private String currencyName;

    @ApiModelProperty(value = "中文幣別", required = true)
    private String currencyChName;

    @ApiModelProperty
    private Date updatedTime;
}
