package com.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "cars")
@EqualsAndHashCode(callSuper = true)
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

    @NotNull
    @NotBlank
    @Column(name = "release_year")
    private Integer releaseYear;

}
