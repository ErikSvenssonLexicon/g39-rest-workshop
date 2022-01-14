package com.example.g39restworkshop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "library_users")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate regDate;
    private String name;
    @Column(unique = true)
    private String email;

    @PrePersist
    void prePersist(){
        regDate = LocalDate.now();
    }

}
