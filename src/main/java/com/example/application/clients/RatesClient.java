package com.example.application.clients;

import com.example.application.requestsdata.RatesInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${ratesUrl}", name = "Rates-Client")
public interface RatesClient {

    @GetMapping("${latestRatesUrl}" + "${ratesApiID}")
    RatesInfo getInfoAboutLatestRatesToUSD();

    @GetMapping("${yesterdayRatesUrl}" + "${ratesApiID}")
    RatesInfo getInfoAboutYesterdayRatesToUSD(@PathVariable String yesterday);
}

