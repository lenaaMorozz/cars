package com.example.car_catalog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "car")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String licensePlate;
    private String color;
    private String brand;
    private int manufacturingYear;
    @CreationTimestamp
    private LocalDateTime createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return manufacturingYear == carEntity.manufacturingYear
               && Objects.equals(licensePlate, carEntity.licensePlate)
               && Objects.equals(color, carEntity.color)
               && Objects.equals(brand, carEntity.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, color, brand, manufacturingYear);
    }
}
