package com.example.car_catalog.service;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.api.response.CarResponse;
import com.example.car_catalog.api.response.CarResponseStatistics;
import com.example.car_catalog.entity.CarEntity;
import com.example.car_catalog.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                        CarResponse.builder()
                                .licensePlate(carEntity.getLicensePlate())
                                .manufacturingYear(carEntity.getManufacturingYear())
                                .id(carEntity.getId())
                                .color(carEntity.getColor())
                                .brand(carEntity.getBrand())
                                .build())
                .toList();
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteCar(long id) {
        if (carRepository.findById(id).isEmpty()) {
            throw new RuntimeException();
        }
        carRepository.deleteById(id);
    }

    @Override
    public CarResponseStatistics getStatistics() {
        long amountCar = carRepository.count();
        if (amountCar == 0) {
            return CarResponseStatistics.builder()
                    .amountCar(amountCar)
                    .build();
        }
        return CarResponseStatistics.builder()
                .amountCar(amountCar)
                .dateOfFirstCreation(carRepository.
                        findFirstByOrderByCreateDate().getCreateDate())
                .dateOfLastCreation(carRepository
                        .findFirstByOrderByCreateDateDesc().getCreateDate())
                .build();
    }
}
