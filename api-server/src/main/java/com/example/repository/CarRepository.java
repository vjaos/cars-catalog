package com.example.repository;

import com.example.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByCarNumber(@NotNull @NotBlank String carNumber);
    Car findTopByOrderByCreatedDesc();
    Car findTopByOrderByCreated();
}
