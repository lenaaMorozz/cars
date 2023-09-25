package com.example.car_catalog.repository;

import com.example.car_catalog.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

}
