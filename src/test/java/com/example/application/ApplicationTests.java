package com.example.application;

import com.example.application.customexception.WrongInputException;
import com.example.application.restcontroller.ApplicationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

    /*@Autowired
    ApplicationController applicationController;

    @Test
    public void controllerNotNull() {
        assertNotNull(applicationController);
    }

    @Test
    public void ratesClientNotNull() {
        assertNotNull(applicationController.getRatesClient());
    }

    @Test
    public void giphyClientNotNull() {
        assertNotNull(applicationController.getGiphyClient());
    }

    @Test
    public void latestRatesToUSDTest() {
        double returnedResult = applicationController.getLatestRatesToUSD("RUB");
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
