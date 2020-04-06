package com.example.dto;

import com.example.entity.Car;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

/**
 * DTO class that needs to send list of cars to the client
 *
 * @author Vyacheslav Osipov
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CarsListResponse {
    private Integer totalCount;
    private List<Car> carsList;
}

