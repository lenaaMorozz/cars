package com.example.car_catalog.controller;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.api.response.CarResponse;
import com.example.car_catalog.api.response.CarResponseStatistics;
import com.example.car_catalog.entity.CarEntity;
import com.example.car_catalog.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private String message;

    @GetMapping
    public String getCars(Model model) {
        List<CarResponse> cars = carService.getCars();
        model.addAttribute("cars", cars);
        model.addAttribute("message", message);
        message = null;
        return "car-list";
    }

    @PostMapping("/add-car")
    public String addCar(@RequestBody CarRequest request) {
        try {
            CarEntity carEntity = carService.addCar(request);
            log.info("added car by id {}", carEntity.getId());

            return "redirect:/cars";
        } catch (RuntimeException e) {
            log.error("Car not added. This car already exists");
            message = "Car not added. This car already exists";
            return "redirect:/cars";
        }
    }

    @DeleteMapping("/delete-car/{id}")
    public String deleteById(@PathVariable("id") long id) {
        try {
            carService.deleteCar(id);
            log.info("deleted car by id {}", id);
            return "redirect:/cars";
        } catch (RuntimeException e) {
            log.error("Car by {} not deleted. Car not found", id);
            return "redirect:/cars";
        }
    }

    @GetMapping("/statistics")
    public String getStatistics(Model model) {
        CarResponseStatistics statistics = carService.getStatistics();
        model.addAttribute("amountCar", statistics.getAmountCar());
        LocalDateTime firstDate = statistics.getDateOfFirstCreation();
        LocalDateTime lastDate = statistics.getDateOfFirstCreation();
        if (firstDate != null) {
            model.addAttribute("firstDate", firstDate
                    .format(DateTimeFormatter.ofPattern("hh:mm:ss dd.MM.yyyy")));
            model.addAttribute("lastDate", lastDate
                    .format(DateTimeFormatter.ofPattern("hh:mm:ss dd.MM.yyyy")));
        }
        return "car-statistics";
    }
}
