package com.example.application.services;

import com.example.application.customexception.GifNotFoundException;
import com.example.application.customexception.WrongInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RandomGifService {
    private final RatesService ratesService;
    private final GiphyService giphyService;

    public String getResult(String currency) throws WrongInputException, GifNotFoundException {
        if (currency == null) {
            throw new WrongInputException("Wrong input: Currency entry is empty");
        }
        return giphyService.getCorrectUrl(ratesService.isLatestCurrencyHigherThenYesterday(currency));
    }
}
