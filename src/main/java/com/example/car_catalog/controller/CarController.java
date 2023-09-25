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
    public long addCar(@RequestBody CarRequest request) {
        return carService.addCar(request).getId();
    }

    @DeleteMapping("/cars/{id}")
    public  ResponseEntity<String> deleteById(@PathVariable long id) {
            carService.deleteCar(id);
            return ResponseEntity.ok("Car " + id + " deleted");
    }

    @GetMapping("/cars/statistics")
    public CarResponseStatistics getStatistics() {
        return carService.getStatistics();
    }

}
