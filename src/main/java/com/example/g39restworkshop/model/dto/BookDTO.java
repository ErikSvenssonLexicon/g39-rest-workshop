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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Validated
public class BookDTO {
    @NotBlank(groups = {OnPut.class, OnPostLoan.class})
    private Integer id;
    @NotBlank(message = "This field is required")
    private String title;
    private Boolean available = true;
    private Boolean reserved = false;
    @NotNull(message = "This field is required", groups = {OnPut.class, OnPost.class})
    @Positive(message = "Need to be a positive value", groups = {OnPut.class, OnPost.class})
    private Integer maxLoanInDays;
    @NotNull(message = "This field is required", groups = {OnPut.class, OnPost.class})
    @Positive(message = "Need to be a positive value", groups = {OnPut.class, OnPost.class})
    private BigDecimal finePerDay;
    @NotBlank(message = "This field is required", groups = {OnPut.class, OnPost.class})
    @Size(message = "Can be at maximum 255 letters", max = 255)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LoanDTO> loanHistory;
}
