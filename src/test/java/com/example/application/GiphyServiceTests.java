package com.example.application;

import com.example.application.clients.GiphyClient;
import com.example.application.customexception.GifNotFoundException;
import com.example.application.requestsdata.Giphy;
import com.example.application.requestsdata.GiphyInfo;
import com.example.application.services.GiphyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GiphyServiceTests {

    @MockBean
    GiphyClient giphyClient;

    @Autowired
    GiphyService giphyService;

    @Test
    public void getRichGiphyURLTest() {
        Mockito.when(giphyClient.getRichGiphy()).thenReturn(new GiphyInfo(new Giphy("Some url"), new Object()));
        assertNotNull(giphyService.getRichGiphyURL());

        Mockito.when(giphyClient.getRichGiphy()).thenReturn(new GiphyInfo(new Giphy(null), null));
        assertThrows(GifNotFoundException.class,() -> giphyService.getRichGiphyURL());
    }

    @Test
    public void getBrokeGiphyURLTest() {
        Mockito.when(giphyClient.getBrokeGiphy()).thenReturn(new GiphyInfo(new Giphy("Some url"), new Object()));
        assertNotNull(giphyService.getBrokeGiphyURl());

        Mockito.when(giphyClient.getBrokeGiphy()).thenReturn(new GiphyInfo(new Giphy(null), null));
        assertThrows(GifNotFoundException.class,() -> giphyService.getBrokeGiphyURl());
    }

    @Test
    public void getCorrectUrlTest() {
        Mockito.when(giphyClient.getRichGiphy()).thenReturn(new GiphyInfo(new Giphy("Rich"), new Object()));
        assertEquals("Rich", giphyService.getCorrectUrl(true));

        Mockito.when(giphyClient.getBrokeGiphy()).thenReturn(new GiphyInfo(new Giphy("Broke"), new Object()));
        assertEquals("Broke", giphyService.getCorrectUrl(false));

        Mockito.when(giphyClient.getRichGiphy()).thenReturn(new GiphyInfo(new Giphy(null), null));
        assertThrows(GifNotFoundException.class, () -> giphyService.getCorrectUrl(true));

        Mockito.when(giphyClient.getBrokeGiphy()).thenReturn(new GiphyInfo(new Giphy(null), null));
        assertThrows(GifNotFoundException.class, () -> giphyService.getCorrectUrl(false));

    }

}
