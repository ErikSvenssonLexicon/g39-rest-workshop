package com.example.g39restworkshop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    private String title;
    private boolean available;
    private boolean reserved;
    private Integer maxLoanInDays;
    private BigDecimal finePerDay;
    private String description;
}
