package com.example.g39restworkshop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"description"})
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private boolean available;
    private boolean reserved;
    private Integer maxLoanInDays;
    private BigDecimal finePerDay;
    private String description;
}
