package com.example.application.restcontroller;

import com.example.application.services.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
@PropertySource(value = "classpath:application.properties")
@RequiredArgsConstructor
public class CurrencyGifController {
    private final GifService service;

    @GetMapping(value = {"/","/{currency}"})
    public String getGifURL(@PathVariable(required = false) String currency) {
       return service.getResult(currency);
    }
}