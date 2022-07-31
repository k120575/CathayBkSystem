package com.example.cathaybksystem.service;

import com.example.cathaybksystem.dto.req.CreateCurrencyParam;
import com.example.cathaybksystem.dto.req.UpdateCurrencyParam;
import com.example.cathaybksystem.dto.rsp.CurrencyResult;

import java.util.List;

public interface CurrencyService {

    /**
     * 取得全部幣別及對應中文名稱
     */
    List<CurrencyResult> getAllCurrency();

    /**
     * 取得幣別及中文
     */
    CurrencyResult getCurrency(String currencyName);

    /**
     * 新增幣別
     */
    CurrencyResult createCurrency(CreateCurrencyParam param);

    /**
     * 修改幣別
     */
    CurrencyResult updateCurrency(String currencyName, UpdateCurrencyParam param);

    /**
     * 刪除幣別
     */
    void deleteCurrency(String currencyName);
}
