package com.example.service.impl;

import com.example.dto.CarStatisticResponse;
import com.example.entity.Car;
import com.example.exception.CarAlreadyExistsException;
import com.example.exception.CarNotFoundException;
import com.example.repository.CarRepository;
import com.example.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link CarService}
 *
 * @author Vyacheslav Osipovl
 */
@Slf4j
@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * @return List of Car entities
     */
    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    /**
     * Method that will create new car entity
     *
     * @param carData cara data to be saved
     * @return created entity, it will never be null
     * @throws CarAlreadyExistsException if car with given number already exists
     */
    @Override
    public Car createCar(@NotNull Car carData) throws CarAlreadyExistsException {
        Optional<Car> existing = carRepository.findByCarNumber(carData.getCarNumber());

        existing.ifPresent(
                car -> {
                    log.warn(String.format(
                            "IN METHOD createCar: Car with number %s already exists",
                            car.getCarNumber()));
                    throw new CarAlreadyExistsException(car.getCarNumber());
                });

        return carRepository.save(carData);
    }

    /**
     * Delete car according to given id
     *
     * @param id of car to be deleted
     * @throws CarNotFoundException if car with give id does not exists
     */
    @Override
    public void deleteCarById(Long id) throws CarNotFoundException {
        carRepository
                .findById(id)
                .ifPresentOrElse(
                        car -> carRepository.delete(car),
                        () -> {
                            log.warn(String.format("IN METHOD deleteCarById: Car with id %d not found", id));
                            throw new CarNotFoundException(id);
                        }
                );

    }

    @Override
    public CarStatisticResponse getCarStatistic() {
        CarStatisticResponse response = new CarStatisticResponse();

        response.setTotalCount(carRepository.count());
        response.setFirstCreated(carRepository.findTopByOrderByCreated());
        response.setLastCreated(carRepository.findTopByOrderByCreatedDesc());

        return response;
    }

}
