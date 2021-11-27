package com.example.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import static org.junit.jupiter.api.Assertions.*;

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
        ResponseEntity<String> response = testRestTemplate.getForEntity(uriBuilder.toUriString(), String.class);
        assertSame(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testControllerBadRequest() {
        String url = "http://localhost:" + port + "/asdf";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<String> response = testRestTemplate.getForEntity(uriBuilder.toUriString(), String.class);
        assertSame(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testControllerBadRequestEmptyData() {
        String url = "http://localhost:" + port + "/";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<String> response = testRestTemplate.getForEntity(uriBuilder.toUriString(), String.class);
        assertSame(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
