package com.example.cathaybksystem.dto.rsp;

import com.example.cathaybksystem.model.Time;
import lombok.Data;

import java.util.Map;

@Data
public class CoindeskResult {

     private Time time;

     Map<String, CoindeskCurrencyResult> bpi;
}
