/*package com.example.REST.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class RestServiceApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;


   @Container
    private final GenericContainer<?> devApp = new GenericContainer<>("restservice")
            .withExposedPorts(5500);


    @Test
    void testTransfer() {
        Integer devPort = devApp.getMappedPort(5500);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("cardFromNumber", "string");
        requestBody.put("cardFromValidTill", "string");
        requestBody.put("cardFromCVV", "string");
        requestBody.put("cardToNumber", "string");

        Map<String, Object> amount = new HashMap<>();
        amount.put("value", 0);
        amount.put("currency", "string");
        requestBody.put("amount", amount);


        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);


        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + devPort + "/transfer/transfer",
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

  /*  @Test
    void testConfirmOperation() {
        Integer prodPort = devApp.getMappedPort(5500);
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + prodPort + "/transfer/confirmOperation",
                String.class
        );
        assertEquals("This is production profile", response.getBody());
    }

} */
