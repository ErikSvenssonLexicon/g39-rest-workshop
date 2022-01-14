package com.example.g39restworkshop.service.implementations;

import com.example.g39restworkshop.data.BookDAO;
import com.example.g39restworkshop.exception.AppEntityNotFoundException;
import com.example.g39restworkshop.model.dto.BookDTO;
import com.example.g39restworkshop.model.entity.Book;
import com.example.g39restworkshop.service.interfaces.BookEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookEntityServiceImpl implements BookEntityService {

    private final BookDAO bookDAO;

    @Override
    public List<Book> findByReserved(boolean reserved) {
        return bookDAO.findByReservedStatus(reserved);
    }

    @Override
    public List<Book> findByAvailable(boolean available) {
        return bookDAO.findByAvailableStatus(available);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookDAO.findByTitle(title);
    }

    @Override
    public Book create(BookDTO dto) {
        if(dto == null) throw new IllegalArgumentException("Dto was null");
        Book book = new Book();
        book = internalCopy(book, dto);

        return bookDAO.save(book);
    }

    @Override
    public Book findById(Integer id) {
        return bookDAO.findById(id)
                .orElseThrow(() -> new AppEntityNotFoundException("Could not find book with id " + id));
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    public Book internalCopy(Book book, BookDTO dto){
        book.setAvailable(dto.getAvailable());
        book.setDescription(dto.getDescription());
        book.setFinePerDay(dto.getFinePerDay());
        book.setReserved(dto.getAvailable());
        book.setMaxLoanInDays(dto.getMaxLoanInDays());
        book.setTitle(dto.getTitle());
        return book;
    }

    @Override
    public Book update(Integer id, BookDTO dto) {
        if(dto == null) throw new IllegalArgumentException("Dto was null");
        Book book = findById(id);
        book = internalCopy(book, dto);
        return bookDAO.save(book);
    }

    @Override
    public boolean delete(Integer id) {
        bookDAO.deleteById(id);
        return !bookDAO.existsById(id);
    }
}
