package com.example.cathaybksystem;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.cathaybksystem.dto.req.CreateCurrencyParam;
import com.example.cathaybksystem.dto.req.UpdateCurrencyParam;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Objects;

public class CathayBkSystemUnitTest {

    String currencyUrl = "http://localhost:8080/api/currency";

    String coindeskUrl = "http://localhost:8080/api/coindesk";

    // 取得全部幣別及對應中文名稱
    @Test
    public void getAllCurrency(){
        String result = HttpUtil.get(currencyUrl + "/getAllCurrency");
        if (!Objects.equals(result, "")){
            System.out.println(JSONUtil.toJsonPrettyStr(result));
        } else {
            System.out.println("查詢不到資料");
        }
    }

    // 取得幣別及中文
    @Test
    public void getCurrency(){
        String currencyName = "USD";
        String result = HttpUtil.get(currencyUrl + "/getCurrency/" + currencyName);
        if (!Objects.equals(result, "")){
            System.out.println(JSONUtil.toJsonPrettyStr(result));
        } else {
            System.out.println("查詢不到資料");
        }
    }

    // 新增幣別
    @Test
    public void createCurrency(){
        CreateCurrencyParam param = new CreateCurrencyParam();
        param.setCurrencyName("USD");
        param.setCurrencyChName("美元");
        param.setCreatedTime(new Date());
        param.setUpdatedTime(new Date());
        String result = HttpUtil.post(currencyUrl + "/createCurrency", JSONUtil.toJsonStr(param));
        if (!Objects.equals(result, "")){
            System.out.println(JSONUtil.toJsonPrettyStr(result));
        } else {
            System.out.println("幣別已存在或參數錯誤");
        }
    }

    // 更新幣別
    @Test
    public void updateCurrency(){
        String currencyName = "US";
        UpdateCurrencyParam param = new UpdateCurrencyParam();
        param.setCurrencyName("USD");
        param.setCurrencyChName("美元");
        param.setUpdatedTime(new Date());
        String result = HttpUtil.post(currencyUrl + "/updateCurrency/" + currencyName, JSONUtil.toJsonStr(param));
        if (!Objects.equals(result, "")){
            System.out.println(JSONUtil.toJsonPrettyStr(result));
        } else {
            System.out.println("幣別不存在或參數錯誤");
        }
    }

    // 刪除幣別
    @Test
    public void deleteCurrency(){
        String currencyName = "GBP";
        String currency = HttpUtil.get(currencyUrl + "/getCurrency/" + currencyName);
        if (!Objects.equals(currency, "")){
            HttpRequest.delete(currencyUrl + "/deleteCurrency/" + currencyName).execute().body();
            System.out.println("刪除完成");
        } else {
            System.out.println("幣別不存在或參數錯誤");
        }
    }

    // 呼叫coindesk api
    @Test
    public void callCoindesk(){
        String result = HttpUtil.get(coindeskUrl + "/callCoindesk");
        if (!Objects.equals(result, "")){
            System.out.println(JSONUtil.toJsonPrettyStr(result));
        } else {
            System.out.println("查無資料");
        }
    }

    // 新coindesk api
    @Test
    public void newCoindesk(){
        String result = HttpUtil.get(coindeskUrl + "/newCoindesk");
        if (!Objects.equals(result, "")){
            System.out.println(JSONUtil.toJsonPrettyStr(result));
        } else {
            System.out.println("查無資料");
        }
    }
}
