package com.example.g39restworkshop.service.interfaces;

import com.example.g39restworkshop.model.dto.LoanDTO;

import java.util.List;

public interface LoanService extends GenericCRUD<LoanDTO, LoanDTO, Integer> {
    List<LoanDTO> findByBookId(Integer bookId);
    List<LoanDTO> findByUserId(Integer userId);
    List<LoanDTO> findByConcluded(boolean concluded);
    LoanDTO concludeLoan(Integer id, Integer userId);
    LoanDTO extendLoan(Integer id, Integer userId, Integer days);
}
