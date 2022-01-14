package com.example.g39restworkshop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_library_user", table = "loans")
    private LibraryUser loanTaker;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_book", table = "loans")
    private Book libraryBook;
    private LocalDate loanDate;
    private boolean concluded;

    public boolean isOverDue(){
        LocalDate dueDate = loanDate.plusDays(libraryBook.getMaxLoanInDays());
        return LocalDate.now().isAfter(dueDate);
    }

    public BigDecimal getFine(){
        BigDecimal fine = BigDecimal.ZERO;
        if(isOverDue()){
            Period period = Period.between(loanDate.plusDays(libraryBook.getMaxLoanInDays()), LocalDate.now());
            long daysOverdue = period.getDays();
            fine = BigDecimal.valueOf(libraryBook.getFinePerDay().longValue() * daysOverdue);        }
        return fine;
    }

    public void setConcluded(boolean concluded){
        this.concluded = concluded;
        libraryBook.setAvailable(true);
    }

    public boolean extendLoan(int days){
        if(days < 1 || libraryBook.isReserved()){
            return false;
        }
        setLoanDate(loanDate.plusDays(days));
        return true;
    }

}
