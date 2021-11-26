package com.example.application.restcontroller;

import com.example.application.services.ApplicationService;
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
       return new ResponseEntity<>(service.getResult(currency), HttpStatus.OK);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> customHandler(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}