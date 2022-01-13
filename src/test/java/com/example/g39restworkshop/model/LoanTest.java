package com.example.g39restworkshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private Book book;
    private Customer user;

    @BeforeEach
    void setUp() {
        book = new Book(
                1, "Test title", true, false, 10, BigDecimal.TEN, "Test description"
        );
        user = new Customer(
                1, LocalDate.parse("2021-12-13"), "Nils Nilsson", "nils@gmail.com"
        );
    }

    @Test
    @DisplayName("Loan is not overdue on last day")
    void isOverDue_false_on_last_day() {
        Loan loan = new Loan(
                1, user, book, LocalDate.now().minusDays(book.getMaxLoanInDays()), false
        );
        loan.setLoanTaker(user);

        assertFalse(loan.isOverDue());
    }

    @Test
    @DisplayName("Loan is overdue on first day after expiration")
    void isOverdue_true() {
        Loan loan = new Loan(
                1, user, book, LocalDate.now().minusDays(book.getMaxLoanInDays() + 1), false
        );
        loan.setLoanTaker(user);

        assertTrue(loan.isOverDue());
    }

    @Test
    @DisplayName("getFine return value of 0 on non overdue book")
    void getFine_return_0() {
        BigDecimal expected = BigDecimal.valueOf(0);
        Loan loan = new Loan(
                1, user, book, LocalDate.now().minusDays(book.getMaxLoanInDays()), false
        );

        assertEquals(expected, loan.getFine());
    }

    @Test
    @DisplayName("getFine return 10 on book overdue by one day")
    void getFine_return_10() {
        BigDecimal expected = BigDecimal.valueOf(10);
        Loan loan = new Loan(
                1, user, book, LocalDate.now().minusDays(book.getMaxLoanInDays() + 1), false
        );

    }

    @Test
    void setTerminated() {
    }

    @Test
    void extendLoan() {
    }
}