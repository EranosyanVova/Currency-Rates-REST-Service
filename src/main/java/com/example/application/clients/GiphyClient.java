package com.example.application.clients;

import com.example.application.requestsdata.GiphyInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${giphyUrl}", name = "Giphy-Client")
public interface GiphyClient {

    @GetMapping("${richGiphyUrl}" + "${giphyApiID}")
    GiphyInfo getRichGiphy();

    @GetMapping("${brokeGiphyUrl}" + "${giphyApiID}")
    GiphyInfo getBrokeGiphy();
}
