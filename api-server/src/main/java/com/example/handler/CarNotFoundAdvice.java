package com.example.handler;

import com.example.dto.ApiMessageResponse;
import com.example.exception.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class CarNotFoundAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CarNotFoundException.class)
    ApiMessageResponse carNotFoundHandler(CarNotFoundException ex) {
        ApiMessageResponse response = new ApiMessageResponse();

        response.setMessage(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setTimestamp(LocalDateTime.now());

        return response;
    }
}
