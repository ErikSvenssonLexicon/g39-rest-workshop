package com.example.g39restworkshop.controller;

import com.example.g39restworkshop.exception.AppEntityNotFoundException;
import com.example.g39restworkshop.model.dto.GeneralExceptionResponse;
import com.example.g39restworkshop.model.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class MyControllerAdvice {

    public static final String VALIDATIONS_FAILED = "One or several validations failed";

    GeneralExceptionResponse build(HttpStatus status, String message, WebRequest request){
        GeneralExceptionResponse generalExceptionResponse = new GeneralExceptionResponse();
        generalExceptionResponse.setTimeStamp(LocalDateTime.now());
        generalExceptionResponse.setError(status.name());
        generalExceptionResponse.setStatus(status.value());
        generalExceptionResponse.setMessage(message);
        generalExceptionResponse.setPath(request.getDescription(false));
        return generalExceptionResponse;
    }

    @ExceptionHandler(AppEntityNotFoundException.class)
    public ResponseEntity<GeneralExceptionResponse> handleAppEntityNotFoundException(AppEntityNotFoundException ex, WebRequest request){
        return ResponseEntity.status(404).body(build(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GeneralExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        return ResponseEntity.badRequest().body(build(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<GeneralExceptionResponse> handleIllegalStateException(IllegalStateException ex, WebRequest request){
        return ResponseEntity.badRequest().body(build(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<GeneralExceptionResponse> handleNumberFormatException(NumberFormatException ex, WebRequest request){
        return ResponseEntity.badRequest().body(build(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setError(HttpStatus.BAD_REQUEST.name());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setPath(request.getDescription(false));
        response.setMessage(VALIDATIONS_FAILED);

        List<String> fields = ex.getBindingResult().getFieldErrors().stream()
                .distinct()
                .map(FieldError::getField)
                .collect(Collectors.toList());

        Map<String, List<String>> errors = new HashMap<>();

        for(String field : fields){
            List<String> list = new ArrayList<>();
            for(FieldError fieldError : ex.getFieldErrors(field)){
                list.add(fieldError.getDefaultMessage());
            }
            errors.put(field, list);
        }

        response.setViolations(errors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setTimeStamp(LocalDateTime.now());
        response.setError(HttpStatus.BAD_REQUEST.name());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setPath(request.getDescription(false));
        response.setMessage(VALIDATIONS_FAILED);

        List<String> fields = ex.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath().toString())
                .distinct()
                .collect(Collectors.toList());

        Map<String, List<String>> errorMap = new HashMap<>();

        for(String field : fields){
            List<String> errors = ex.getConstraintViolations().stream()
                    .filter(constraintViolation -> constraintViolation.getPropertyPath().toString().equals(field))
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            errorMap.put(field, errors);
        }
        response.setViolations(errorMap);

        return ResponseEntity.badRequest().body(response);
    }

}
