package com.example.application.restcontroller;

import com.example.application.clients.RatesClient;
import com.example.application.services.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class CurrencyGifController {

    private final GifService service;
    private final RatesClient ratesClient;

    @GetMapping("/{currency}")
    public void getGifURL(@PathVariable(required = false) String currency,
                          HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", service.getResult(currency));
        httpServletResponse.setStatus(302);
    }

}