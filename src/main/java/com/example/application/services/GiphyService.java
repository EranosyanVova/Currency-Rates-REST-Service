package com.example.application.services;

import com.example.application.clients.GiphyClient;
import com.example.application.customexception.GifNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiphyService {
    private final GiphyClient giphyClient;

    public String getCorrectUrl(boolean richUrlStatus) {
        return richUrlStatus ? getRichGiphyURL() : getBrokeGiphyURl();
    }

    private String getRichGiphyURL() {
        String richGiphyURL = giphyClient.getRichGiphy().getData().getUrl();
        return Optional.ofNullable(richGiphyURL).
                orElseThrow(() -> new GifNotFoundException("Gif not found"));
    }

    private String getBrokeGiphyURl() {
        String brokeGiphyURL = giphyClient.getBrokeGiphy().getData().getUrl();
        return Optional.ofNullable(brokeGiphyURL).
                orElseThrow(() -> new GifNotFoundException("Gif not found"));
    }
}
