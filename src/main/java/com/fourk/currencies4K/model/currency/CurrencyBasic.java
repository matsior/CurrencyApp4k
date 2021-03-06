package com.fourk.currencies4K.model.currency;

import lombok.Getter;

@Getter
public class CurrencyBasic extends Currency{
    private Double mid;

    public CurrencyBasic(String currency, String code, Double mid) {
        super(currency, code);
        this.mid = mid;
    }
}
