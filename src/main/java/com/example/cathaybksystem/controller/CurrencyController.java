package com.example.cathaybksystem.controller;

import com.example.cathaybksystem.dto.req.CreateCurrencyParam;
import com.example.cathaybksystem.dto.req.UpdateCurrencyParam;
import com.example.cathaybksystem.dto.rsp.CurrencyResult;
import com.example.cathaybksystem.model.Currency;
import com.example.cathaybksystem.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Currency API")
@RestController
@RequestMapping(value = "/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @ApiOperation(value = "取得全部幣別", notes = "列出所有幣別")
    @GetMapping(value = "/getAllCurrency")
    public List<CurrencyResult> getAllCurrency(){
        return currencyService.getAllCurrency();
    }

    @ApiOperation(value = "取得幣別及中文", notes = "取得幣別及中文")
    @GetMapping(value = "/getCurrency/{currencyName}")
    public CurrencyResult getCurrency(
            @ApiParam(required = true, name = "currencyName", value = "幣別") @PathVariable String currencyName){
        return currencyService.getCurrency(currencyName);
    }

    @ApiOperation(value = "新增幣別", notes = "新增幣別")
    @PostMapping(value = "/createCurrency")
    public CurrencyResult createCurrency(
            @ApiParam(required = true, name = "currency", value = "幣別") @RequestBody CreateCurrencyParam param){
        return currencyService.createCurrency(param);
    }

    @ApiOperation(value = "修改幣別", notes = "修改幣別")
    @PostMapping(value = "/updateCurrency/{currencyName}")
    public CurrencyResult updateCurrency(
            @ApiParam(required = true, name = "currencyName", value = "幣別") @PathVariable String currencyName,
            @ApiParam(required = true) @RequestBody UpdateCurrencyParam param){
        return currencyService.updateCurrency(currencyName, param);
    }

    @ApiOperation(value = "刪除幣別", notes = "刪除幣別")
    @DeleteMapping(value = "/deleteCurrency/{currencyName}")
    public void deleteCurrency(
            @ApiParam(required = true, name = "currency", value = "幣別") @PathVariable String currencyName){
        currencyService.deleteCurrency(currencyName);
    }
}
