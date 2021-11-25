package com.example.application.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class ApplicationService {
    private final RandomGifService randomGifService;
}
