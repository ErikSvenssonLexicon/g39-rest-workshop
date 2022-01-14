package com.example.g39restworkshop.service.implementations;

import com.example.g39restworkshop.data.LoanDAO;
import com.example.g39restworkshop.exception.AppEntityNotFoundException;
import com.example.g39restworkshop.model.dto.LoanDTO;
import com.example.g39restworkshop.model.entity.Book;
import com.example.g39restworkshop.model.entity.LibraryUser;
import com.example.g39restworkshop.model.entity.Loan;
import com.example.g39restworkshop.service.interfaces.BookEntityService;
import com.example.g39restworkshop.service.interfaces.LibraryUserEntityService;
import com.example.g39restworkshop.service.interfaces.LoanEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanEntityServiceImpl implements LoanEntityService {

    private final LoanDAO loanDAO;
    private final BookEntityService bookEntityService;
    private final LibraryUserEntityService libraryUserEntityService;

    @Override
    public Loan create(LoanDTO dto) {
        if(dto == null) throw new IllegalArgumentException("Dto was null");
        if(dto.getLoanTaker() == null || dto.getLibraryBook() == null){
            throw new IllegalArgumentException("dto.loanTaker or dto.libraryBook was null");
        }
        Book book = bookEntityService.findById(dto.getLibraryBook().getId());
        LibraryUser user = libraryUserEntityService.findById(dto.getLoanTaker().getId());

        if(book.isAvailable()){
            Loan loan = new Loan();
            loan.setLoanTaker(user);
            loan.setConcluded(false);
            loan.setLibraryBook(book);
            loan.setLoanDate(LocalDate.now());
            return loanDAO.save(loan);
        }else{
            throw new IllegalStateException("Book is currently not available");
        }
    }

    @Override
    public Loan findById(Integer id) {
        return loanDAO.findById(id)
                .orElseThrow(() -> new AppEntityNotFoundException("Loan with id " + id + " could not be found"));
    }

    @Override
    public List<Loan> findAll() {
        return loanDAO.findAll();
    }

    @Override
    public Loan update(Integer id, LoanDTO dto) {
        if(dto == null) throw new IllegalStateException("Dto was null");
        Loan loan = findById(id);

        loan.setLoanDate(dto.getLoanDate());
        loan.setLoanTaker(
                dto.getLoanTaker() == null ? null : libraryUserEntityService.findById(dto.getLoanTaker().getId())
        );

        loan.setLibraryBook(
                dto.getLibraryBook() == null ? null : bookEntityService.findById(dto.getLibraryBook().getId())
        );

        loan.setConcluded(dto.isConcluded());
        loan.setLoanDate(dto.getLoanDate());

        return loanDAO.save(loan);
    }

    @Override
    public boolean delete(Integer id) {
        loanDAO.deleteById(id);
        return !loanDAO.existsById(id);
    }

    @Override
    public List<Loan> findByBookId(Integer bookId) {
        return loanDAO.findByBookId(bookId);
    }

    @Override
    public List<Loan> findByUserId(Integer userId) {
        return loanDAO.findByUserId(userId);
    }

    @Override
    public List<Loan> findByConcluded(boolean concluded) {
        return loanDAO.findByConcludedStatus(concluded);
    }
}