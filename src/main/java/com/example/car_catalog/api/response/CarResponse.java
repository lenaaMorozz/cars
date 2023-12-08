package com.example.car_catalog.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@AllArgsConstructor
@Builder
public class CarResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private String brand;
    private int manufacturingYear;

}
