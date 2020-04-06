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
    @NotBlank(message = "Car number cannot be empty")
    @Column(name = "car_number", unique = true)
    private String carNumber;

    @NotNull
    @NotBlank(message = "Brand cannot be empty")
    @Column(name = "brand")
    private String brand;

    @NotNull
    @NotBlank(message = "Color cannot be empty")
    @Column(name = "color")
    private String color;

    @Column(name = "release_year")
    private Integer releaseYear;

}
