package com.example.svn1;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class Svc1Application {

    public static void main(String[] args) {
        SpringApplication.run(Svc1Application.class, args);
    }

    @RestController
    @RequestMapping
    @AllArgsConstructor
    public static class MyController {

        @GetMapping("/")
        public String crashThis() {
            return "Hi from SVC 1";
        }
    }
}
