package com.example.handler;

import com.example.dto.ApiMessageResponse;
import com.example.exception.CarAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class CarAlreadyExistsAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CarAlreadyExistsException.class)
    ApiMessageResponse carAlreadyExistsHandler(CarAlreadyExistsException ex) {
        ApiMessageResponse response = new ApiMessageResponse();

        response.setMessage(ex.getMessage());
        response.setStatus(HttpStatus.CONFLICT);
        response.setTimestamp(LocalDateTime.now());

        return response;
    }
}
