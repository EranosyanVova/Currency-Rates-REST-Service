package com.example.application;

import com.example.application.clients.RatesClient;
import com.example.application.customexception.WrongInputException;
import com.example.application.requestsdata.RatesInfo;
import com.example.application.services.RatesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RatesServiceTests {

    @MockBean
    RatesClient ratesClient;

    @Autowired
    RatesService ratesService;

    @Test
    void getLatestRateToUSDTest() {
        Map<String, Double> mockRates = new HashMap<>();
        mockRates.put("RUB", 70.5);
        mockRates.put("AMD", 1.0);
        mockRates.put("EUR", 0.85);

        Mockito.when(ratesClient.getInfoAboutLatestRatesToUSD()).thenReturn(new RatesInfo("USD", mockRates));

        double rubRate = ratesService.getLatestRateToUSD("RUB");
        double amdRate = ratesService.getLatestRateToUSD("AMD");
        double eurRate = ratesService.getLatestRateToUSD("EUR");

        assertEquals(0, Double.compare(rubRate, 70.5));
        assertEquals(0, Double.compare(amdRate, 1.0));
        assertEquals(0, Double.compare(eurRate, 0.85));
        assertThrows(WrongInputException.class, () -> ratesService.getLatestRateToUSD("asd"));
    }

    @Test
    void getYesterdayRateToUSDTest() {
        Map<String, Double> mockRates = new HashMap<>();
        mockRates.put("RUB", 70.5);
        mockRates.put("AMD", 1.0);
        mockRates.put("EUR", 0.85);

        String yesterdayDate = LocalDate.now().minusDays(1).toString();
        Mockito.when(ratesClient.getInfoAboutYesterdayRatesToUSD(yesterdayDate)).thenReturn(new RatesInfo("USD", mockRates));

        double rubRate = ratesService.getYesterdayRateToUsd("RUB");
        double amdRate = ratesService.getYesterdayRateToUsd("AMD");
        double eurRate = ratesService.getYesterdayRateToUsd("EUR");

        assertEquals(0, Double.compare(rubRate, 70.5));
        assertEquals(0, Double.compare(amdRate, 1.0));
        assertEquals(0, Double.compare(eurRate, 0.85));
        assertThrows(WrongInputException.class, () -> ratesService.getYesterdayRateToUsd("asd"));
    }

    @Test
    void isLatestCurrencyHigherThenYesterdayTest() {
        Map<String, Double> mockRatesLatest = new HashMap<>();
        mockRatesLatest.put("RUB", 70.5);
        mockRatesLatest.put("AMD", 1.0);
        mockRatesLatest.put("EUR", 0.85);

        Mockito.when(ratesClient.getInfoAboutLatestRatesToUSD()).thenReturn(new RatesInfo("USD", mockRatesLatest));

        Map<String, Double> mockRatesYesterday = new HashMap<>();
        mockRatesYesterday.put("RUB", 70.0);
        mockRatesYesterday.put("AMD", 1.1);
        mockRatesYesterday.put("EUR", 0.85);

        String yesterdayDate = LocalDate.now().minusDays(1).toString();
        Mockito.when(ratesClient.getInfoAboutYesterdayRatesToUSD(yesterdayDate)).thenReturn(new RatesInfo("USD", mockRatesYesterday));

        assertTrue(ratesService.isLatestCurrencyHigherThenYesterday("RUB"));
        assertTrue(ratesService.isLatestCurrencyHigherThenYesterday("EUR"));
        assertFalse(ratesService.isLatestCurrencyHigherThenYesterday("AMD"));
        assertThrows(WrongInputException.class, () -> ratesService.isLatestCurrencyHigherThenYesterday("asd"));
    }


}
