package com.example.cathaybksystem.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.cathaybksystem.dto.rsp.CoindeskResult;
import com.example.cathaybksystem.service.CoindeskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Api(tags = "Coindesk API")
@RestController
@RequestMapping(value = "/api/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @ApiOperation(value = "呼叫coindesk api", notes = "呼叫coindesk api")
    @GetMapping(value = "/callCoindesk")
    public String callCoindesk(){
        String coindesk = HttpUtil.get("https://api.coindesk.com/v1/bpi/currentprice.json");
        return JSONUtil.toJsonPrettyStr(coindesk);
    }

    @ApiOperation(value = "新coindesk api", notes = "新coindesk api")
    @GetMapping(value = "/newCoindesk")
    public CoindeskResult newCoindesk() throws ParseException {
        return coindeskService.newCoindesk();
    }
}
