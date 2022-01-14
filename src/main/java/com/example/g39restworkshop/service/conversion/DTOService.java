package com.example.g39restworkshop.service.conversion;

import com.example.g39restworkshop.model.dto.BookDTO;
import com.example.g39restworkshop.model.dto.LibraryUserDTO;
import com.example.g39restworkshop.model.dto.LoanDTO;
import com.example.g39restworkshop.model.entity.Book;
import com.example.g39restworkshop.model.entity.LibraryUser;
import com.example.g39restworkshop.model.entity.Loan;

import java.util.List;

public interface DTOService {
    BookDTO toSmallDTO(Book book);
    BookDTO toFullDTO(Book book, List<Loan> loanHistory);
    LibraryUserDTO toSmallDTO(LibraryUser libraryUser);
    LibraryUserDTO toFullDTO(LibraryUser libraryUser, List<Loan> activeLoans);
    LoanDTO toSmallDTO(Loan loan);
    LoanDTO toFullDTO(Loan loan);
}
