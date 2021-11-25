package com.example.application.restcontroller;

import com.example.application.services.ApplicationService;
import com.example.application.customexception.WrongInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@PropertySource(value = "classpath:app.properties")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService service;

    @GetMapping(value = {"/","/{currency}"})
    public ResponseEntity<String> getResult(@PathVariable(required = false) String currency) {
       String url;
       try {
           url = service.getRandomGifService().getResult(currency);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(url, HttpStatus.OK);
    }
}