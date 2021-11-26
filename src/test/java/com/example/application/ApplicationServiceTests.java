package com.example.application;

import com.example.application.customexception.WrongInputException;
import com.example.application.services.ApplicationService;
import com.example.application.services.GiphyService;
import com.example.application.services.RatesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationServiceTests {

    @MockBean
    RatesService ratesService;

    @MockBean
    GiphyService giphyService;

    @Autowired
    ApplicationService applicationService;

    @Test
    public void getResultTest() {
        Mockito.when(ratesService.isLatestCurrencyHigherThenYesterday(Mockito.anyString())).thenReturn(true);
        Mockito.when(giphyService.getCorrectUrl(true)).thenReturn("Rich");

        assertEquals("Rich", applicationService.getResult("RUB"));

        Mockito.when(ratesService.isLatestCurrencyHigherThenYesterday(Mockito.anyString())).thenReturn(false);
        Mockito.when(giphyService.getCorrectUrl(false)).thenReturn("Broke");

        assertEquals("Broke", applicationService.getResult("RUB"));

        assertThrows(WrongInputException.class, () -> applicationService.getResult(null));

    }


}
