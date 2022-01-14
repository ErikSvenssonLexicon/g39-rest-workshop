package com.example.g39restworkshop.service.interfaces;

import com.example.g39restworkshop.model.dto.LoanDTO;
import com.example.g39restworkshop.model.entity.Loan;

import java.util.List;

public interface LoanEntityService extends GenericCRUD<Loan, LoanDTO, Integer> {
    List<Loan> findByBookId(Integer bookId);
    List<Loan> findByUserId(Integer userId);
    List<Loan> findByConcluded(boolean concluded);
    Loan concludeLoan(Integer id, Integer userId);
    Loan extendLoan(Integer id, Integer userId, Integer days);

}
