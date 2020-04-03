package com.example.dto;

import com.example.entity.Car;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CarStatisticResponse {
    Long totalCount;
    Car lastCreated;
    Car firstCreated;
}
