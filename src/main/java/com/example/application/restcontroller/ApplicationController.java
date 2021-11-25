package com.example.application.restcontroller;

import com.example.application.clients.GiphyClient;
import com.example.application.clients.RatesClient;
import com.example.application.customexception.WrongInputException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@PropertySource(value = "classpath:app.properties")
@Data
public class ApplicationController {
    static final double WRONG_INPUT_CODE = -1.0;

    @Autowired
    private RatesClient ratesClient;

    @Autowired
    private GiphyClient giphyClient;

    @GetMapping(value = {"/","/{currency}"})
    public ResponseEntity<String> getResult(@PathVariable(required = false) String currency) throws WrongInputException {
        if (currency == null) {
            throw new WrongInputException("Wrong input: Currency entry is empty");
        }
        Double rateToUsdToday = getLatestRatesToUSD(currency.toUpperCase());
        if (rateToUsdToday == WRONG_INPUT_CODE) {
            throw new WrongInputException("Wrong input: " + currency);
        }

        Double rateToUsdYesterday = getYesterdayRatesToUsd(currency.toUpperCase());
        if (rateToUsdToday >= rateToUsdYesterday) {
            return new ResponseEntity<>(getRichGiphyURL(), HttpStatus.OK);
        }

        return new ResponseEntity<>(getBrokeGiphyURl(), HttpStatus.OK);
    }

    public Double getLatestRatesToUSD(String currency) {
        if (ratesClient.getInfoAboutLatestRatesToUSD().getRates().containsKey(currency)) {
            return ratesClient.getInfoAboutLatestRatesToUSD().getRates().get(currency);
        }
        return WRONG_INPUT_CODE;
    }

    public Double getYesterdayRatesToUsd(String currency) {
        String yesterdayDate = LocalDate.now().minusDays(1).toString();
        if (ratesClient.getInfoAboutYesterdayRatesToUSD(yesterdayDate).getRates().containsKey(currency)) {
            return ratesClient.getInfoAboutYesterdayRatesToUSD(yesterdayDate).getRates().get(currency);
        }
        return WRONG_INPUT_CODE;
    }

    public String getRichGiphyURL() {
        return giphyClient.getRichGiphy().getData().getUrl();
    }

    public String getBrokeGiphyURl() {
        return giphyClient.getBrokeGiphy().getData().getUrl();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongInputException.class)
    public String handleException(WrongInputException e) {
        return e.getMessage();
    }
}