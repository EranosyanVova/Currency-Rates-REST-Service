package com.example.application.requestsdata;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RatesInfo {
    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, Double> rates;
}
