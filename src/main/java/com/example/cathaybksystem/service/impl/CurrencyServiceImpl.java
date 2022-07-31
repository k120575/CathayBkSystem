package com.example.cathaybksystem.service.impl;

import com.example.cathaybksystem.dto.req.CreateCurrencyParam;
import com.example.cathaybksystem.dto.req.UpdateCurrencyParam;
import com.example.cathaybksystem.dto.rsp.CurrencyResult;
import com.example.cathaybksystem.model.Currency;
import com.example.cathaybksystem.repository.CurrencyRepository;
import com.example.cathaybksystem.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * 取得全部幣別及對應中文名稱
     */
    @Override
    public List<CurrencyResult> getAllCurrency() {
        List<Currency> currencyList = currencyRepository.getAllCurrency();
        List<CurrencyResult> resultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(currencyList)){
            currencyList.forEach(currency -> {
                CurrencyResult currencyResult = new CurrencyResult();
                currencyResult.setCurrencyName(currency.getCurrencyName());
                currencyResult.setCurrencyChName(currency.getCurrencyChName());
                resultList.add(currencyResult);
            });
            return resultList;
        }
        return null;
    }

    /**
     * 取得幣別及中文
     *
     * @param currencyName
     */
    @Override
    public CurrencyResult getCurrency(String currencyName) {
        Currency currency = currencyRepository.findByCurrencyName(currencyName);
        if (Objects.nonNull(currency)){
            CurrencyResult result = new CurrencyResult();
            result.setCurrencyName(currency.getCurrencyName());
            result.setCurrencyChName(currency.getCurrencyChName());
            return result;
        }
        return null;
    }

    /**
     * 新增幣別
     *
     * @param param
     */
    @Override
    public CurrencyResult createCurrency(CreateCurrencyParam param) {
        Currency currency = currencyRepository.findByCurrencyName(param.getCurrencyName());
        if (Objects.isNull(currency)){
            Currency newCurrency = new Currency();
            newCurrency.setCurrencyName(param.getCurrencyName());
            newCurrency.setCurrencyChName(param.getCurrencyChName());
            newCurrency.setCreatedTime(new Date());
            newCurrency.setUpdatedTime(new Date());
            currencyRepository.saveAndFlush(newCurrency);
            CurrencyResult result = new CurrencyResult();
            result.setCurrencyName(newCurrency.getCurrencyName());
            result.setCurrencyChName(newCurrency.getCurrencyChName());
            return result;
        }
        return null;
    }

    /**
     * 修改幣別
     *
     * @param currencyName
     * @param param
     */
    @Override
    public CurrencyResult updateCurrency(String currencyName, UpdateCurrencyParam param) {
        Currency currency = currencyRepository.findByCurrencyName(currencyName);
        if (Objects.nonNull(currency)){
            currency.setCurrencyName(param.getCurrencyName());
            currency.setCurrencyChName(param.getCurrencyChName());
            currency.setUpdatedTime(new Date());
            currencyRepository.saveAndFlush(currency);
            CurrencyResult result = new CurrencyResult();
            result.setCurrencyName(currency.getCurrencyName());
            result.setCurrencyChName(currency.getCurrencyChName());
            return result;
        }
        return null;
    }

    /**
     * 刪除幣別
     *
     * @param currencyName
     */
    @Override
    public void deleteCurrency(String currencyName) {
        Currency currency = currencyRepository.findByCurrencyName(currencyName);
        if (Objects.nonNull(currency)){
            currencyRepository.deleteById(currency.getId());
        }
    }
}
