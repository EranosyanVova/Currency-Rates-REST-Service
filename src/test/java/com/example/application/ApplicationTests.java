package com.example.application;

import com.example.application.customexception.WrongInputException;
import com.example.application.services.ApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

    /*@Autowired
    ApplicationService service;

    @Test
    public void controllerNotNull() {
        assertNotNull(service);
    }

    @Test
    public void ratesClientNotNull() {
        assertNotNull(service.getRandomGifService().getRatesClient());
    }

    @Test
    public void giphyClientNotNull() {
        assertNotNull(service.getRandomGifService().getGiphyClient());
    }

    @Test
    public void latestRatesToUSDTest() throws WrongInputException {
        double returnedResult = service.getRandomGifService().getLatestRatesToUSD("RUB");
        assertTrue(returnedResult > 0 || returnedResult == -1.0);
    }

    @Test
    public void yesterdayRatesToUSDTest() {
        double returnedResult = applicationController.getYesterdayRatesToUsd("RUB");
        assertTrue(returnedResult > 0 || returnedResult == -1.0);
    }

    @Test
    public void richGiphyURLNotNull() {
        assertNotNull(applicationController.getRichGiphyURL());
    }

    @Test
    public void brokeGiphyURLNotNull() {
        assertNotNull(applicationController.getBrokeGiphyURl());
    }

    @Test
    public void resultExceptionNullString(){
        assertThrows(WrongInputException.class, () -> applicationController.getResult(""));
    }

    @Test
    public void resultExceptionWrongString(){
        assertThrows(WrongInputException.class, () -> applicationController.getResult("a"));
    }

    @Test
    public void resultNotNull() throws WrongInputException {
        assertNotNull(applicationController.getResult("RUB").getBody());
    }


*/

}
