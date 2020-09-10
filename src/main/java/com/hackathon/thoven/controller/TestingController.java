package com.hackathon.thoven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/thoven")
public class TestingController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello!";
    }
}
