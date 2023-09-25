package com.example.car_catalog.api.request;

import lombok.Data;

@Data
public class CarRequest {
    private String licensePlate;
    private String color;
    private String brand;
    private int manufacturingYear;
}
