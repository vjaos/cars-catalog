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
import java.text.SimpleDateFormat;
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

    /**
     * Fetch statistic from database as follows:
     * <ul>
     *     <li>Amount of cars</li>
     *     <li>Date of first created car</li>
     *     <li>Date of last created car</li>
     *     <li>Amount of purple cars</li>
     *     <li>Amount of red cars</li>
     * </ul>
     * <p>
     * Notes: Actually, I didn't think that is the best way to fetch database statistic,
     * but I didn't find the way to get statistic that will be easy to implement.
     *
     * @return {@link CarStatisticResponse}
     */
    @Override
    public CarStatisticResponse getCarStatistic() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String firstDate = "No data";
        String lastDate = "No data";

        long totalCount = carRepository.count();
        Car firstCreatedCar = carRepository.findTopByOrderByCreated();
        Car lastCreatedCar = carRepository.findTopByOrderByCreatedDesc();
        Integer amountOfPurple = carRepository.countAllByColor("Purple");
        Integer amountOfRed = carRepository.countAllByColor("Red");

        if (firstCreatedCar != null) {
            firstDate = simpleDateFormat.format(firstCreatedCar.getCreated());
        }
        if (lastCreatedCar != null) {
            lastDate = simpleDateFormat.format(lastCreatedCar.getCreated());
        }

        return new CarStatisticResponse(
                totalCount,
                firstDate,
                lastDate,
                amountOfPurple,
                amountOfRed
        );
    }

}
