package com.example.takaful_assessment.controllers;

import com.example.takaful_assessment.payloads.responses.GenericErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class, RuntimeException.class})
    public ResponseEntity<?> handleGenericExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(new GenericErrorResponse(true, ex.getMessage()));
    }
}
