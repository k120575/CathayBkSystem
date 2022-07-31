package com.example.cathaybksystem.service;

import com.example.cathaybksystem.dto.req.CreateCurrencyParam;
import com.example.cathaybksystem.dto.req.UpdateCurrencyParam;
import com.example.cathaybksystem.dto.rsp.CoindeskResult;
import com.example.cathaybksystem.dto.rsp.CurrencyResult;

import java.text.ParseException;
import java.util.List;

public interface CoindeskService {

    /**
     * 取得新coindesk api
     */
    CoindeskResult newCoindesk() throws ParseException;
}
