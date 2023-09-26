package com.example.car_catalog.controller;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.api.response.CarResponse;
import com.example.car_catalog.api.response.CarResponseStatistics;
import com.example.car_catalog.entity.CarEntity;
import com.example.car_catalog.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Slf4j
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
           CarEntity carEntity = carService.addCar(request);
           log.info("added car by id {}", carEntity.getId());
           return ResponseEntity.ok("Car added, id: " + carEntity.getId());
       }
       catch (RuntimeException e) {
           log.error("Car not added. This car already exists");
           return ResponseEntity.badRequest().body("This car already exists");
       }
    }

    @DeleteMapping("/cars/{id}")
    public  ResponseEntity<String> deleteById(@PathVariable long id) {
            try {
                carService.deleteCar(id);
                log.info("deleted car by id {}", id);
                return ResponseEntity.ok("Car " + id + " deleted");
            }
            catch (RuntimeException e) {
                log.error("Car by {} not deleted. Car not found", id);
                return ResponseEntity.badRequest().body("Car not found");
            }

    }

    @GetMapping("/cars/statistics")
    public CarResponseStatistics getStatistics() {
        return carService.getStatistics();
    }
}
