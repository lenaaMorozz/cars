package com.example.car_catalog;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.api.response.CarResponse;
import com.example.car_catalog.api.response.CarResponseStatistics;
import com.example.car_catalog.controller.CarController;
import com.example.car_catalog.entity.CarEntity;
import com.example.car_catalog.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    @Mock
    private CarService carService;
    @InjectMocks
    private CarController carController;
    @Mock
    private CarResponse carResponse;
    @Mock
    private Model model;

    @Test
    public void getCar_shouldCallServiceAndReturnView() {
        when(carService.getCars())
                .thenReturn(Collections.singletonList(carResponse));

        String view = carController.getCars(model);

        verify(model).addAttribute("cars", Collections.singletonList(carResponse));
        verify(model).addAttribute("message", null);
        verify(carService, Mockito.times(1)).getCars();
        assertEquals("car-list", view);
    }

    @Test
    public void addCar_shouldAddCarAndReturnView() {
        String licensePlate = "A100AA159";
        String color = "Black";
        String brand = "Skoda";
        int manufacturingYear = 2023;

        CarEntity carEntity = CarEntity.builder()
                .id(1L)
                .licensePlate("A100AA159")
                .color("Black")
                .brand("Skoda")
                .manufacturingYear(2023)
                .createDate(LocalDateTime.now())
                .build();

        when(carService.addCar(any(CarRequest.class)))
                .thenReturn(carEntity);

        String view = carController.addCar(licensePlate, color, brand, manufacturingYear);

        assertEquals("redirect:/cars", view);
        verify(carService, Mockito.times(1))
                .addCar(any(CarRequest.class));
    }

    @Test
    public void deleteById_shouldCallServiceAndReturnView() {
        String view = carController.deleteById(anyLong());

        verify(carService, Mockito.times(1)).deleteCar(anyLong());
        assertEquals("redirect:/cars", view);
    }


    @Test
    public void getStatistics_shouldCallServiceAndReturnView() {
        int amountCar = 3;
        LocalDateTime firstDate = LocalDateTime.now();
        LocalDateTime lastDate = LocalDateTime.now();

        CarResponseStatistics carResponseStatistics = CarResponseStatistics
                .builder()
                .amountCar(amountCar)
                .dateOfFirstCreation(firstDate)
                .dateOfLastCreation(lastDate)
                .build();
        when(carService.getStatistics()).thenReturn(carResponseStatistics);

        String view = carController.getStatistics(model);

        assertEquals("car-statistics", view);
        verify(carService, Mockito.times(1)).getStatistics();
    }
}
