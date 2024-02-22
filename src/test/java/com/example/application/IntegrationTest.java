package com.example.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testControllerOk() {
        String url = "http://localhost:" + port + "/RUB";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<Void> response = testRestTemplate.getForEntity(uriBuilder.toUriString(), Void.class);
        assertSame(FOUND, response.getStatusCode());
    }

    @Test
    public void testControllerBadRequest() {
        String url = "http://localhost:" + port + "/asdf";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<Void> response = testRestTemplate.getForEntity(uriBuilder.toUriString(), Void.class);
        assertSame(BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testControllerBadRequestEmptyData() {
        String url = "http://localhost:" + port + "/";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<Void> response = testRestTemplate.getForEntity(uriBuilder.toUriString(), Void.class);
        assertSame(NOT_FOUND, response.getStatusCode());
    }
}
