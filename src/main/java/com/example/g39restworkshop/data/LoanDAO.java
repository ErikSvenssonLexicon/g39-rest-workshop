package com.example.g39restworkshop.data;

import com.example.g39restworkshop.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanDAO extends JpaRepository<Loan, Integer> {
    @Query("SELECT l FROM Loan l WHERE l.loanTaker.id = :userId")
    List<Loan> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT l FROM Loan l WHERE l.libraryBook = :bookId")
    List<Loan> findByBookId(@Param("bookId") Integer bookId);

    @Query("SELECT l FROM Loan l WHERE l.concluded = :concluded")
    List<Loan> findByConcludedStatus(@Param("concluded") boolean concluded);
}
