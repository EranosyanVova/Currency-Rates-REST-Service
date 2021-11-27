package com.example.application;

import com.example.application.customexception.WrongInputException;
import com.example.application.services.GifService;
import com.example.application.services.GiphyService;
import com.example.application.services.RatesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GifServiceTest {

    @MockBean
    RatesService ratesService;

    @MockBean
    GiphyService giphyService;

    @Autowired
    GifService applicationService;

    @Test
    public void getResultReturnURLTest() {
        Mockito.when(ratesService.isLatestCurrencyHigherThenYesterday(Mockito.anyString())).thenReturn(true);
        Mockito.when(giphyService.getCorrectUrl(true)).thenReturn("Rich");

        assertEquals("Rich", applicationService.getResult("RUB"));

        Mockito.when(ratesService.isLatestCurrencyHigherThenYesterday(Mockito.anyString())).thenReturn(false);
        Mockito.when(giphyService.getCorrectUrl(false)).thenReturn("Broke");

        assertEquals("Broke", applicationService.getResult("RUB"));
    }

    @Test
    public void getResultExceptionTest() {
        assertThrows(WrongInputException.class, () -> applicationService.getResult(null));
    }
}
