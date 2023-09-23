package com.example.car_catalog.controller;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.api.response.CarResponse;
import com.example.car_catalog.api.response.CarResponseStatistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {
    @GetMapping("/cars")
    public List<CarResponse> getCar() {
        return new ArrayList<>();
    }

    @PostMapping("/")
    public ResponseEntity<String> addCar(@RequestBody CarRequest request) {
        return ResponseEntity.ok("The car {} added");
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable long id) {
        return ResponseEntity.ok(id);
    }

    @GetMapping("/cars/statistics")
    public CarResponseStatistics getStatistics() {
        return new CarResponseStatistics(3, LocalDateTime.now(), LocalDateTime.now());
    }

}
