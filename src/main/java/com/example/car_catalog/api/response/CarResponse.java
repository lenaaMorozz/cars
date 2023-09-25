package com.example.car_catalog.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CarResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private String brand;
    private int manufacturingYear;

}
