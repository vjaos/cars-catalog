package com.example.dto;

import com.example.entity.Car;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CarsListResponse {
    private Integer totalCount;
    private List<Car> carsList;
}
