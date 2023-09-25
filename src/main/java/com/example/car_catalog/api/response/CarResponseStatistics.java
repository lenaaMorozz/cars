package com.example.car_catalog.api.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CarResponseStatistics {
    private long amountCar;
    private LocalDateTime dateOfFirstCreation;
    private LocalDateTime dateOfLastCreation;

}
