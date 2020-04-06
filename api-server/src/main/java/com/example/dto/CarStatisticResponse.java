package com.example.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO class that needs to represent database statistics in specific form
 *
 * @author Vyacheslav Osipov
 */
@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CarStatisticResponse {
    Long totalCount;
    String firstCreatedDate;
    String lastCreatedDate;
    Integer amountOfPurpleCars;
    Integer amountOfRedCars;
}
