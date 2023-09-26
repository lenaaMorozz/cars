package com.example.car_catalog.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    private String licensePlate;
    private String color;
    private String brand;
    private int manufacturingYear;
}
