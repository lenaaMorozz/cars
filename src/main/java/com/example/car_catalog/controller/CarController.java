package com.example.car_catalog.controller;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.api.response.CarResponse;
import com.example.car_catalog.api.response.CarResponseStatistics;
import com.example.car_catalog.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public List<CarResponse> getCars() {
        return carService.getCars();
    }

    @PostMapping("/")
    public ResponseEntity<String> addCar(@RequestBody CarRequest request) {
       try {
           return ResponseEntity.ok("Car added, id: " + carService.addCar(request).getId());
       }
       catch (RuntimeException e) {
           return ResponseEntity.badRequest().body("This car already exists");
       }
    }

    @DeleteMapping("/cars/{id}")
    public  ResponseEntity<String> deleteById(@PathVariable long id) {
            try {
                carService.deleteCar(id);
                return ResponseEntity.ok("Car " + id + " deleted");
            }
            catch (RuntimeException e) {
                return ResponseEntity.badRequest().body("Car not found");
            }

    }

    @GetMapping("/cars/statistics")
    public CarResponseStatistics getStatistics() {
        return carService.getStatistics();
    }
}
