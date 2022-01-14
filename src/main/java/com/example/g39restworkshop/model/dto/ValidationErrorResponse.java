package com.example.g39restworkshop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorResponse extends GeneralExceptionResponse{
    private Map<String, List<String>> violations;
}
