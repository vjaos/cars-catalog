package com.example.service;

import com.example.entity.Car;
import com.example.exception.CarAlreadyExistsException;
import com.example.exception.CarNotFoundException;
import com.example.repository.CarRepository;
import com.example.service.impl.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarServiceTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    private Car car;

    @BeforeEach
    public void init() {
        initMocks(this);
        initCar();
    }


    @Test
    public void shouldReturnCarList() {
        carService.getAll();
        verify(carRepository, times(1)).findAll();
    }


    @Test
    public void shouldCreateCar() {
        carService.createCar(car);

        verify(carRepository, times(1))
                .save(car);
    }

    @Test
    public void whenCarExists_thenThrowCarAlreadyExistsException() {
        doReturn(Optional.of(car))
                .when(carRepository)
                .findByCarNumber(car.getCarNumber());

        assertThrows(CarAlreadyExistsException.class, () -> carService.createCar(car));
    }


    @Test
    public void shouldDeleteCar() {
        doReturn(Optional.of(car))
                .when(carRepository)
                .findById(car.getId());

        carService.deleteCarById(car.getId());

        verify(carRepository, times(1))
                .delete(car);
    }


    @Test
    public void whenCarWithGivenIdDoesNotExists_thenThrowCarNotFoundException() {
        assertThrows(CarNotFoundException.class, () -> carService.deleteCarById(car.getId()));
    }

    @Test
    public void shouldCollectStatistics() {
        carService.getCarStatistic();

        verify(carRepository, times(1))
                .count();
        verify(carRepository, times(1))
                .findTopByOrderByCreated();
        verify(carRepository, times(1))
                .findTopByOrderByCreatedDesc();
        verify(carRepository, times(2))
                .countAllByColor(anyString());

    }

    private void initCar() {
        Car car = new Car();
        car.setId(1L);
        car.setBrand("b");
        car.setColor("c");
        car.setReleaseYear(1);
        car.setCarNumber("cn");

        this.car = car;
    }
}
