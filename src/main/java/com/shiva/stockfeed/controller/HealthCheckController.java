package com.shiva.stockfeed.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;  

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public String healthCheck(){
        return "UP";
    }

    @GetMapping("/live")
    public String healthCheck(){
        return "live";
    }
}
