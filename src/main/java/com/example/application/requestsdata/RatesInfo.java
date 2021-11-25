package com.example.application.requestsdata;

import lombok.*;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatesInfo {
    private String base;
    private Map<String, Double> rates;
}
