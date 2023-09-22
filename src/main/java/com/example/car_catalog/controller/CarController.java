package com.example.car_catalog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {
    @GetMapping
    public ResponseEntity getCar() {
        return ResponseEntity.ok("fjjf");
    }
}
