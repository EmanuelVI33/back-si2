package com.back_si2.back_si2.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Hello {
    @GetMapping("/app")
    public String index() {
        return "Hello World";
    }

}
