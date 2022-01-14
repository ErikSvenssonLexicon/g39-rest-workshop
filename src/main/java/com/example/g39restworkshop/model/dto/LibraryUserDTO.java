package com.example.g39restworkshop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LibraryUserDTO {
    private Integer id;
    private LocalDate regDate;
    private String name;
    private String email;
}
