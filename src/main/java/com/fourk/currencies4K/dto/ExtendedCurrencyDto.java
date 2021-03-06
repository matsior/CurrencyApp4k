package com.fourk.currencies4K.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExtendedCurrencyDto {
    private String currency;
    private String code;
    private Double bid;
    private Double ask;
}
