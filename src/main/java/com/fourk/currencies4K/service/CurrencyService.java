package com.fourk.currencies4K.service;

import com.fourk.currencies4K.dao.UsersCurrenciesDao;
import com.fourk.currencies4K.utils.CurrencyDataSource;
import com.fourk.currencies4K.dto.BasicCurrencyDto;
import com.fourk.currencies4K.dto.ExtendedCurrencyDto;
import com.fourk.currencies4K.model.currency.CurrencyBasic;
import com.fourk.currencies4K.model.currency.CurrencyExtended;

import java.util.List;
import java.util.stream.Collectors;

public class CurrencyService {

    CurrencyDataSource currencyDataSource = new CurrencyDataSource();
    UsersCurrenciesDao usersCurrenciesDao = new UsersCurrenciesDao();
    CurrencyMapper currencyMapper = new CurrencyMapper();

    public List<BasicCurrencyDto> getUserCurrencies(int userId) {
        return usersCurrenciesDao.findUserSavedCurrenciesByUserId(userId)
                .stream()
                .map(currencyDataSource::createCurrencyFromCode)
                .map(currencyMapper::map)
                .collect(Collectors.toList());
    }

    public List<BasicCurrencyDto> getBasicCurrencies() {
        return currencyDataSource.createBasicCurrencyList()
                .stream()
                .map(currencyMapper::map)
                .collect(Collectors.toList());
    }

    public List<ExtendedCurrencyDto> getExtendedCurrencies() {
        return currencyDataSource.createExtendedCurrencyList()
                .stream()
                .map(currencyMapper::map)
                .collect(Collectors.toList());
    }

    public BasicCurrencyDto getArchivalCurrency(String code, String date) {
        CurrencyBasic currencyBasic = currencyDataSource.createCurrencyFromCode(code, date);
        return currencyMapper.map(currencyBasic);
    }

    public void save(int userId, String currencyCode) {
        usersCurrenciesDao.save(userId, currencyCode);
    }

    private static class CurrencyMapper {

        BasicCurrencyDto map(CurrencyBasic currencyBasic) {
            return new BasicCurrencyDto(
                    currencyBasic.getCurrency(),
                    currencyBasic.getCode(),
                    currencyBasic.getMid()
            );
        }

        ExtendedCurrencyDto map(CurrencyExtended currencyExtended) {
            return new ExtendedCurrencyDto(
                    currencyExtended.getCurrency(),
                    currencyExtended.getCode(),
                    currencyExtended.getBid(),
                    currencyExtended.getAsk()
            );
        }
    }
}
