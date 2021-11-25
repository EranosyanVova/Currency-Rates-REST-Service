package com.example.application.services;

import com.example.application.clients.GiphyClient;
import com.example.application.customexception.GifNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiphyService {
    private final GiphyClient giphyClient;

    public String getCorrectUrl(boolean richUrl) throws GifNotFoundException {
        return richUrl ? getRichGiphyURL() : getBrokeGiphyURl();
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
