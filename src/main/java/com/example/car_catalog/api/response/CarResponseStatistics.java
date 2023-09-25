package com.example.car_catalog.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CarResponseStatistics {
    private int amountCar;
    private LocalDateTime dateOfFirstCreation;
    private LocalDateTime dateOfLastCreation;

}
