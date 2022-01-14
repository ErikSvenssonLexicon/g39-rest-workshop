package com.example.g39restworkshop.model.dto;

import com.example.g39restworkshop.validation.groups.OnPostLoan;
import com.example.g39restworkshop.validation.groups.OnPut;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class LoanDTO {
    @NotBlank(groups = {OnPut.class})
    private Integer id;

    @NotNull(groups = {OnPostLoan.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Valid private LibraryUserDTO loanTaker;

    @NotNull(groups = {OnPostLoan.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Valid private BookDTO libraryBook;

    private LocalDate loanDate;
    private Boolean concluded;
}
