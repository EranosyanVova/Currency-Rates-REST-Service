package com.example.application.services;

import com.example.application.customexception.WrongInputException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class GifService {
    private final RatesService ratesService;
    private final GiphyService giphyService;

    public String getResult(String currency) {
        if (currency == null) {
            throw new WrongInputException("Wrong input: Currency entry is empty");
        }
        boolean isLatestCurrency = ratesService.isLatestCurrencyHigherThenYesterday(currency);
        return giphyService.getCorrectUrl(isLatestCurrency);
    }
}
