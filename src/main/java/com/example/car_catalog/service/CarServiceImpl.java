package com.example.car_catalog.service;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.api.response.CarResponse;
import com.example.car_catalog.api.response.CarResponseStatistics;
import com.example.car_catalog.entity.CarEntity;
import com.example.car_catalog.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<CarResponse> getCars() {
        return carRepository.findAll()
                .stream()
                .map(carEntity ->
                        new CarResponse(carEntity.getId(), carEntity.getLicensePlate(), carEntity.getColor(),
                                carEntity.getBrand(), carEntity.getManufacturingYear()))
                .toList();
    }

    @Override
    public CarEntity addCar(CarRequest request) {
        CarEntity newCar = CarEntity.builder()
                .licensePlate(request.getLicensePlate())
                .color(request.getColor())
                .brand(request.getBrand())
                .manufacturingYear(request.getManufacturingYear())
                .build();

        List<CarEntity> carList = carRepository.findAll();
        if (carList.stream()
                .anyMatch(carEntity -> carEntity.equals(newCar))) {
            throw new RuntimeException();
        }

        return carRepository.save(newCar);
    }

    @Override
    public void deleteCar(long id) {
        if (carRepository.findById(id).isEmpty()) {
            throw new RuntimeException();
        }
        carRepository.deleteById(id);
    }

    @Override
    public CarResponseStatistics getStatistics() {
        return null;
    }
}
