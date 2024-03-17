package com.example.devops.lab2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class HelloWorldController {
    @GetMapping("")
    public String helloWorld() {
        LocalTime currentTime = LocalTime.now();

        if (currentTime.isAfter(LocalTime.NOON)) {
            return "Good morning, Kwok Yuk Chui";
        } else {
            return "Good morning, Kwok Yuk Chui";
        }
    }
}