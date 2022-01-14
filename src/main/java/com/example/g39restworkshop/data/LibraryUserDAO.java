package com.example.g39restworkshop.data;

import com.example.g39restworkshop.model.entity.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibraryUserDAO extends JpaRepository<LibraryUser, Integer> {
    @Query("SELECT u FROM LibraryUser u WHERE UPPER(u.email) = UPPER(:email)")
    Optional<LibraryUser> findByEmail(@Param("email") String email);

}
