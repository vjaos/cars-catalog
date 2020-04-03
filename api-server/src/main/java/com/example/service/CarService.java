package com.example.service;

import com.example.dto.CarStatisticResponse;
import com.example.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> getAll();

    Car createCar(Car carData);

    void deleteCarById(Long id);

    CarStatisticResponse getCarStatistic();

}
