package com.example.REST.service.ExceptionHandler;


import com.example.REST.service.Exceptions.AppException;

import com.example.REST.service.Models.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<String> handlerAppException(AppException ex) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString(new ErrorResponse(ex.getMessage(), 0));
        return ResponseEntity.badRequest().body(responseJson);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerGlobalException(Exception ex) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString(new ErrorResponse(ex.getMessage(), 0));
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(responseJson);

    }

}
