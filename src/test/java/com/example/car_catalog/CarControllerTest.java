package com.example.car_catalog;

import com.example.car_catalog.api.request.CarRequest;
import com.example.car_catalog.api.response.CarResponse;
import com.example.car_catalog.controller.CarController;
import com.example.car_catalog.entity.CarEntity;
import com.example.car_catalog.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    @Mock
    private CarService carService;
    @InjectMocks
    private CarController carController;
    @Mock
    private CarRequest request;
    @Mock
    private CarResponse carResponse;

    @Test
    public void getCar_shouldCallService() {
        when(carService.getCars())
                .thenReturn(Collections.singletonList(carResponse));

        carController.getCars();

        verify(carService, Mockito.times(1)).getCars();

    }

    @Test
    public void addCar_shouldAddCar() {
        ResponseEntity<String> response = ResponseEntity.ok("Car added, id: 1");
        CarEntity carEntity = CarEntity.builder()
                .id(1L)
                .build();

        when(carService.addCar(any(CarRequest.class)))
                .thenReturn(carEntity);

        ResponseEntity<String> actualResponse = carController.addCar(request);

        assertEquals(response, actualResponse);
        verify(carService, Mockito.times(1))
                .addCar(any(CarRequest.class));
    }

    @Test
    public void deleteCar_shouldCallService() {
        carController.deleteById(anyLong());

        verify(carService, Mockito.times(1)).deleteCar(anyLong());
    }

    @Test
    public void getStatistics_shouldCallService() {
        carController.getStatistics();

        verify(carService, Mockito.times(1)).getStatistics();
    }

}
