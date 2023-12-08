CREATE DATABASE IF NOT EXISTS cars;
USE cars;

CREATE TABLE IF NOT EXISTS car
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    license_plate      VARCHAR(255),
    color              VARCHAR(255),
    brand              VARCHAR(255),
    manufacturing_year INT,
    create_date        DATETIME(6)
);