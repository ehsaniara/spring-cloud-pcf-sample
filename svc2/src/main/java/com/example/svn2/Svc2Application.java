package com.example.svn2;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class Svc2Application {

    public static void main(String[] args) {
        SpringApplication.run(Svc2Application.class, args);
    }

    @RestController
    @RequestMapping
    @AllArgsConstructor
    public static class MyController {

        @GetMapping("/")
        public String crashThis(@RequestHeader(value = "id") String id) {
            return String.format("Hi from SVC 2, your id: %s", id);
        }
    }
}
