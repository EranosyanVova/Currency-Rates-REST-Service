package com.example.application.services;

import com.example.application.clients.RatesClient;
import com.example.application.customexception.WrongInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RatesService {
    private final RatesClient ratesClient;

    public boolean isLatestCurrencyHigherThenYesterday(String currency) {
        double rateToUsdToday = getLatestRateToUSD(currency);
        double rateToUsdYesterday = getYesterdayRateToUsd(currency);
        return rateToUsdToday >= rateToUsdYesterday;
    }

    private double getLatestRateToUSD(String currency) {
        Map<String, Double> ratesToUsd = ratesClient.getInfoAboutLatestRatesToUSD().getRates();
        String currencyRate = currency.toUpperCase();

        if (ratesToUsd.containsKey(currencyRate)) {
            return ratesToUsd.get(currencyRate);
        } else {
            throw new WrongInputException("Wrong input: " + currency);
        }
    }

    private double getYesterdayRateToUsd(String currency) {
        String yesterdayDate = LocalDate.now().minusDays(1).toString();
        Map<String, Double> ratesToUsd = ratesClient.getInfoAboutYesterdayRatesToUSD(yesterdayDate).getRates();
        String currencyRate = currency.toUpperCase();

        if (ratesToUsd.containsKey(currencyRate)) {
            return ratesToUsd.get(currencyRate);
        } else {
            throw new WrongInputException("Wrong input: " + currency);
        }
    }
}
