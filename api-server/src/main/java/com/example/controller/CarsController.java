package com.example.controller;

import com.example.config.CarsCatalogConstants;
import com.example.dto.CarStatisticResponse;
import com.example.dto.CarsListResponse;
import com.example.entity.Car;
import com.example.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = CarsCatalogConstants.CARS_PATH)
public class CarsController {

    private CarService carService;

    @Autowired
    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCarsList() {

        CarsListResponse response = new CarsListResponse();
        List<Car> cars = carService.getAll();

        response.setTotalCount(cars.size());
        response.setCarsList(cars);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCarInformation(@RequestBody @Valid Car car) {
        return new ResponseEntity<>(carService.createCar(car), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCarInformation(@PathVariable("id") Long id) {

        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/statistics")
    public ResponseEntity<?> getCarsStatistic() {

        CarStatisticResponse statistic = carService.getCarStatistic();
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }
}
