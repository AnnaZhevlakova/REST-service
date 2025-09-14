package com.example.REST.service.ExceptionHandler;


import com.example.REST.service.Controllers.TransferController;
import com.example.REST.service.Exceptions.UserException;

import com.example.REST.service.Models.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(TransferController.class);

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handlerAppException(UserException ex) throws Exception {
        logger.error(String.format("message: %s | stackTrace: %s", ex.getMessage(), ex.getStackTrace().toString()));

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString(new ErrorResponse(ex.getMessage(), 0));
        return ResponseEntity.badRequest().body(responseJson);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerGlobalException(Exception ex) throws Exception {
        logger.error(String.format("message: %s | stackTrace: %s", ex.getMessage(), ex.getStackTrace().toString()));

        ObjectMapper mapper = new ObjectMapper();

        String responseJson = mapper.writeValueAsString(new ErrorResponse("Что то пошло не так.", 0));
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(responseJson);

    }

}
