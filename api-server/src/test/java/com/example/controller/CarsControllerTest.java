package com.example.controller;

import com.example.config.CarsCatalogConstants;
import com.example.entity.Car;
import com.example.repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarsControllerTest {

    private CarRepository carRepository;
    private MockMvc mockMvc;
    private ObjectMapper MAPPER;
    private final static JacksonJsonParser jsonParser = new JacksonJsonParser();
    private final String CAR_NUMBER = "abc123";

    @Autowired
    public CarsControllerTest(CarRepository carRepository,
                              MockMvc mockMvc,
                              ObjectMapper MAPPER) {
        this.carRepository = carRepository;
        this.mockMvc = mockMvc;
        this.MAPPER = MAPPER;
    }

    @Test
    @Sql(value = "/sql/import-car-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/import-car-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldReturnCarsList() throws Exception {
        mockMvc.perform(get(CarsCatalogConstants.CARS_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.total_count", is(1)))
                .andExpect(jsonPath("$.cars_list").isArray());
    }

    @Test
    @Sql(value = "/sql/import-car-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldCreateNewCar() throws Exception {
        Car car = new Car();
        car.setCarNumber("test123");
        car.setReleaseYear(2004);
        car.setColor("Red");
        car.setBrand("Maserati");

        mockMvc.perform(post(CarsCatalogConstants.CARS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.car_number", is(car.getCarNumber())))
                .andExpect(jsonPath("$.color", is(car.getColor())))
                .andExpect(jsonPath("$.release_year", is(car.getReleaseYear())))
                .andExpect(jsonPath("$.brand", is(car.getBrand())));
    }


    @Test
    @Sql(value = "/sql/import-car-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/import-car-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenGivenCarNumberAlreadyInUse_thenReturnErrorMessage() throws Exception {
        Car car = new Car();
        car.setCarNumber(CAR_NUMBER);
        car.setReleaseYear(2000);
        car.setColor("r");
        car.setBrand("b");
        final String errorMessage = String.format("Car with number '%s' already exists", CAR_NUMBER);

        mockMvc.perform(post(CarsCatalogConstants.CARS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(car)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is(errorMessage)));
    }

    @Test
    public void whenGivenIncorrectData_thenStatusIsBadRequest() throws Exception {
        Car car = new Car();

        mockMvc.perform(post(CarsCatalogConstants.CARS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(MAPPER.writeValueAsString(car)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @Sql(value = "/sql/import-car-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/import-car-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldDeleteCar() throws Exception {
        Long carId = carRepository.findByCarNumber(CAR_NUMBER).get().getId();

        mockMvc.perform(delete(CarsCatalogConstants.CARS_PATH + "/" + carId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenDeleteRequestGetWrongId_thenReturnNotFound() throws Exception {
        long id = new Random().nextLong();
        String expectedErrorMessage = "Could not find car with id - " + id;
        mockMvc.perform(delete(CarsCatalogConstants.CARS_PATH + "/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is(expectedErrorMessage)));
    }


    @Test
    @Sql(value = "/sql/import-car-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/import-car-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void shouldReturnStatistic() throws Exception {
        mockMvc.perform(get(CarsCatalogConstants.CARS_PATH + "/statistics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.total_count", is(1)))
                .andExpect(jsonPath("$.first_created_date", is(notNullValue())))
                .andExpect(jsonPath("$.last_created_date", is(notNullValue())));

    }
}
