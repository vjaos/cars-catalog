package com.example.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "cars")
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Car extends BaseEntity {
    @NotNull
    @NotBlank
    @Column(name = "car_number", unique = true)
    private String carNumber;

    @NotNull
    @NotBlank
    @Column(name = "brand")
    private String brand;

    @NotNull
    @NotBlank
    @Column(name = "color")
    private String color;

    @Min(value = 1805, message = "Release year should not be less that 1805")
    @Max(value = 2020, message = "Release year should not be greater that 2020")
    @Column(name = "release_year")
    private Integer releaseYear;

}
