package com.example.g39restworkshop.model.dto;

import com.example.g39restworkshop.validation.groups.OnPost;
import com.example.g39restworkshop.validation.groups.OnPostLoan;
import com.example.g39restworkshop.validation.groups.OnPut;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class LibraryUserDTO {
    @NotBlank(groups = {OnPut.class, OnPostLoan.class})
    private Integer id;
    @Null(groups = OnPost.class)
    private LocalDate regDate;
    @NotBlank(message = "This field is required")
    private String name;
    @NotBlank(message = "This field is required")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LoanDTO> activeLoans;
}
