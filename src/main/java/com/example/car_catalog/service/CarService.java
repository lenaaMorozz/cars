package com.example.car_catalog.service;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.api.response.CarResponse;
import com.example.car_catalog.api.response.CarResponseStatistics;
import com.example.car_catalog.entity.CarEntity;

import java.util.List;

public interface CarService {

    List<CarResponse> getCars();
    CarEntity addCar(CarRequest request);
    void deleteCar(long id);
    CarResponseStatistics getStatistics();

}
