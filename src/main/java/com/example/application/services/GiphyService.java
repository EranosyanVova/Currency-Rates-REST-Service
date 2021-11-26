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

    public String getRichGiphyURL() {
        return Optional.ofNullable(giphyClient.getRichGiphy().getData().getUrl()).orElseThrow(() -> new GifNotFoundException("Gif not found"));
    }

    public String getBrokeGiphyURl() {
        return Optional.ofNullable(giphyClient.getBrokeGiphy().getData().getUrl()).orElseThrow(() -> new GifNotFoundException("Gif not found"));
    }
}
