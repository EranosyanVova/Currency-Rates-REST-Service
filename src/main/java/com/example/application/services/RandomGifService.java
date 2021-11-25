package com.example.application.services;

import com.example.application.clients.GiphyClient;
import com.example.application.clients.RatesClient;
import com.example.application.customexception.GifNotFoundException;
import com.example.application.customexception.WrongInputException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Getter
public class RandomGifService {
    static final double WRONG_INPUT_CODE = -1.0;

    private final RatesClient ratesClient;
    private final GiphyClient giphyClient;

    public String getResult( String currency) throws WrongInputException, GifNotFoundException {
        if (currency == null) {
            throw new WrongInputException("Wrong input: Currency entry is empty");
        }
        double rateToUsdToday = getLatestRatesToUSD(currency);
        double rateToUsdYesterday = getYesterdayRatesToUsd(currency);

        if (rateToUsdToday >= rateToUsdYesterday) {
            return getRichGiphyURL();
        }
        return getBrokeGiphyURl();

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

    private String getRichGiphyURL() throws GifNotFoundException {
        String gifUrl = giphyClient.getRichGiphy().getData().getUrl();
        if (gifUrl != null) {
            return gifUrl;
        } else {
            throw new GifNotFoundException("Gif not found");
        }
    }

    private String getBrokeGiphyURl() throws GifNotFoundException {
        String gifUrl = giphyClient.getBrokeGiphy().getData().getUrl();
        if (gifUrl != null) {
            return gifUrl;
        } else {
            throw new GifNotFoundException("Gif not found");
        }
    }
}
