package com.example.cathaybksystem.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.example.cathaybksystem.dto.rsp.CoindeskCurrencyResult;
import com.example.cathaybksystem.dto.rsp.CoindeskResult;
import com.example.cathaybksystem.dto.rsp.CurrencyResult;
import com.example.cathaybksystem.model.Time;
import com.example.cathaybksystem.service.CoindeskService;
import com.example.cathaybksystem.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CoindeskServiceImpl implements CoindeskService {

    @Autowired
    private CurrencyService currencyService;

    /**
     * 取得新coindesk api
     */
    @Override
    public CoindeskResult newCoindesk() throws ParseException {
        CoindeskResult result = new CoindeskResult();
        String coindesk = HttpUtil.get("https://api.coindesk.com/v1/bpi/currentprice.json");
        JSONObject jsonObject = new JSONObject(coindesk);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdfUTC = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss 'UTC'", Locale.US);
        SimpleDateFormat sdfISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.US);
        SimpleDateFormat sdfuk = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm 'BST'", Locale.UK);
        String updated = jsonObject.getJSONObject("time").getStr("updated");
        String updatedISO = jsonObject.getJSONObject("time").getStr("updatedISO");
        String updateduk = jsonObject.getJSONObject("time").getStr("updateduk");
        Date dateUTC = sdfUTC.parse(updated);
        Date dateISO = sdfISO.parse(updatedISO);
        Date dateuk = sdfuk.parse(updateduk);

        Time time = new Time();
        time.setUpdated(sdf.format(dateUTC));
        time.setUpdatedISO(sdf.format(dateISO));
        time.setUpdateduk(sdf.format(dateuk));

        Set<String> bpiSet = jsonObject.getJSONObject("bpi").getRaw().keySet();
        Map<String, CoindeskCurrencyResult> coindeskCurrencyResultMap = new HashMap<>();
        for (String b : bpiSet){
            CurrencyResult currencyResult = currencyService.getCurrency(b);
            if (Objects.nonNull(currencyResult)){
                CoindeskCurrencyResult coindeskCurrencyResult = new CoindeskCurrencyResult();
                coindeskCurrencyResult.setCurrencyName(currencyResult.getCurrencyName());
                coindeskCurrencyResult.setCurrencyChName(currencyResult.getCurrencyChName());
                coindeskCurrencyResult.setRate(jsonObject.getJSONObject("bpi").getJSONObject(b).getStr("rate"));
                coindeskCurrencyResultMap.put(b, coindeskCurrencyResult);
            }
        }
        result.setTime(time);
        result.setBpi(coindeskCurrencyResultMap);
        return result;
    }
}
