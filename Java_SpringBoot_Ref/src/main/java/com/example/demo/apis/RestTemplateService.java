package com.example.demo.apis;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
@AllArgsConstructor
public class RestTemplateService {
    private final RestTemplate restTemplate;

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType) {
        return restTemplate.getForEntity(url, responseType);
    }
}
