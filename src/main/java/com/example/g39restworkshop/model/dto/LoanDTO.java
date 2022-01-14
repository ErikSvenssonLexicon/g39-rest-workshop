package com.example.g39restworkshop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LoanDTO {
    private Integer id;
    private LibraryUserDTO loanTaker;
    private BookDTO libraryBook;
    private LocalDate loanDate;
    private boolean concluded;
}
