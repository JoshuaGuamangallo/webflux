package com.Joshus.webflux.webflux.api_rest_controller;

import org.springframework.stereotype.Service;
import org.springframework.test.web.reactive.server.WebTestClient;

@Service
public class RestService {
    public WebTestClient restBuilder(){
        return WebTestClient.bindToServer().baseUrl("http://localhost:8080/api/v0/").build();
    }
}
