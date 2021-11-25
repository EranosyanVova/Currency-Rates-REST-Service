package com.example.application.services;

import com.example.application.clients.RatesClient;
import com.example.application.customexception.WrongInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RatesService {
    private final RatesClient ratesClient;

    public boolean isLatestCurrencyHigherThenYesterday(String currency) throws WrongInputException {
        double rateToUsdToday = getLatestRatesToUSD(currency);
        double rateToUsdYesterday = getYesterdayRatesToUsd(currency);
        return rateToUsdToday >= rateToUsdYesterday;
    }

    private double getLatestRatesToUSD(String currency) throws WrongInputException {
        currency = currency.toUpperCase();
        if (ratesClient.getInfoAboutLatestRatesToUSD().getRates().containsKey(currency)) {
            return ratesClient.getInfoAboutLatestRatesToUSD().getRates().get(currency);
        } else {
            throw new WrongInputException("Wrong input: " + currency);
        }
    }

    private double getYesterdayRatesToUsd(String currency) throws WrongInputException {
        currency = currency.toUpperCase();
        String yesterdayDate = LocalDate.now().minusDays(1).toString();
        if (ratesClient.getInfoAboutYesterdayRatesToUSD(yesterdayDate).getRates().containsKey(currency)) {
            return ratesClient.getInfoAboutYesterdayRatesToUSD(yesterdayDate).getRates().get(currency);
        } else {
            throw new WrongInputException("Wrong input: " + currency);
        }
    }
}
