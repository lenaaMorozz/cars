package com.example.car_catalog;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.entity.CarEntity;
import com.example.car_catalog.repository.CarRepository;
import com.example.car_catalog.service.CarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarServiceImpl carService;
    private CarEntity carEntity;

    @BeforeEach
    public void setUp() {
        carEntity = CarEntity.builder()
                .id(1L)
                .licensePlate("A100AA159")
                .brand("Skoda")
                .color("Black")
                .manufacturingYear(2023)
                .createDate(LocalDateTime.now())
                .build();
    }

    @Test
    public void getCar_shouldCallRepository() {
        when(carRepository.findAll())
                .thenReturn(Collections.singletonList(carEntity));

        carService.getCars();

        verify(carRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    public void addCar_shouldSaveCar() {
        when(carRepository.save(any(CarEntity.class)))
                .thenReturn(carEntity);
        when(carRepository.findAll())
                .thenReturn(Collections.singletonList(carEntity));

        CarEntity actual = carService.addCar(new CarRequest());

        Assertions.assertEquals(actual, carEntity);
        Mockito.verify(carRepository, Mockito.times(1))
                .save(any(CarEntity.class));
    }

    @Test
    public void deleteCar_shouldCallRepository() {
        when(carRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(carEntity));

        carService.deleteCar(1L);

        verify(carRepository, Mockito.times(1))
                .deleteById(1L);
    }

    @Test
    public void getStatistics_shouldCallRepositoryTreeTimes() {
        when(carRepository.count()).thenReturn(2L);
        when(carRepository.findFirstByOrderByCreateDate())
                .thenReturn(carEntity);
        when(carRepository.findFirstByOrderByCreateDateDesc())
                .thenReturn(carEntity);

        carService.getStatistics();

        verify(carRepository, Mockito.times(1)).count();
        verify(carRepository, Mockito.times(1)).findFirstByOrderByCreateDate();
        verify(carRepository, Mockito.times(1)).findFirstByOrderByCreateDateDesc();
    }
}
