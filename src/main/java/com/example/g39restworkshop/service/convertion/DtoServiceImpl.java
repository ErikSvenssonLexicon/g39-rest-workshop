package com.example.g39restworkshop.service.convertion;

import com.example.g39restworkshop.model.dto.BookDTO;
import com.example.g39restworkshop.model.dto.LibraryUserDTO;
import com.example.g39restworkshop.model.dto.LoanDTO;
import com.example.g39restworkshop.model.entity.Book;
import com.example.g39restworkshop.model.entity.LibraryUser;
import com.example.g39restworkshop.model.entity.Loan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DtoServiceImpl implements DTOService{

    @Override
    public BookDTO toSmallDTO(Book book) {
        if(book == null) return null;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAvailable(book.isAvailable());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setReserved(book.isReserved());
        bookDTO.setFinePerDay(book.getFinePerDay());
        bookDTO.setMaxLoanInDays(book.getMaxLoanInDays());
        return bookDTO;
    }

    @Override
    public BookDTO toFullDTO(Book book, @NotNull List<Loan> loanHistory) {
        if(book == null) return null;
        BookDTO bookDTO = toSmallDTO(book);
        bookDTO.setLoanHistory(
                loanHistory.stream().map(this::toSmallDTO)
                .collect(Collectors.toList())
        );
        return bookDTO;
    }

    @Override
    public LibraryUserDTO toSmallDTO(LibraryUser libraryUser) {
        if(libraryUser == null) return null;
        LibraryUserDTO libraryUserDTO = new LibraryUserDTO();
        libraryUserDTO.setId(libraryUser.getId());
        libraryUserDTO.setName(libraryUser.getName());
        libraryUserDTO.setRegDate(libraryUser.getRegDate());
        return libraryUserDTO;
    }

    @Override
    public LibraryUserDTO toFullDTO(LibraryUser libraryUser,@NotNull List<Loan> activeLoans) {
        if(libraryUser == null) return null;
        LibraryUserDTO libraryUserDTO = toSmallDTO(libraryUser);
        libraryUserDTO.setActiveLoans(
                activeLoans.stream()
                        .filter(loan -> !loan.isConcluded())
                        .map(this::toSmallDTO)
                        .collect(Collectors.toList())
        );
        return libraryUserDTO;
    }

    @Override
    public LoanDTO toSmallDTO(Loan loan) {
        if(loan == null) return null;
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setLoanDate(loan.getLoanDate());
        loanDTO.setId(loan.getId());
        loan.setConcluded(loan.isConcluded());
        return loanDTO;
    }

    @Override
    public LoanDTO toFullDTO(Loan loan) {
        if(loan == null) return null;
        LoanDTO loanDTO = toSmallDTO(loan);
        loanDTO.setLoanTaker(toSmallDTO(loan.getLoanTaker()));
        loanDTO.setLibraryBook(toSmallDTO(loan.getLibraryBook()));
        return loanDTO;
    }
}
