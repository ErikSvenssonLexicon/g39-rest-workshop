package com.example.g39restworkshop.service.implementations;

import com.example.g39restworkshop.model.dto.BookDTO;
import com.example.g39restworkshop.model.entity.Book;
import com.example.g39restworkshop.service.convertion.DTOService;
import com.example.g39restworkshop.service.interfaces.BookEntityService;
import com.example.g39restworkshop.service.interfaces.BookService;
import com.example.g39restworkshop.service.interfaces.LoanEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookEntityService bookEntityService;
    private final LoanEntityService loanEntityService;
    private final DTOService dtoService;

    @Override
    public List<BookDTO> findByReserved(boolean reserved) {
        return bookEntityService.findByReserved(reserved).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findByAvailable(boolean available) {
        return bookEntityService.findByAvailable(available).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findByTitle(String title) {
        return bookEntityService.findByTitle(title).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO create(BookDTO dto) {
        Book book  = bookEntityService.create(dto);
        return dtoService.toFullDTO(book, new ArrayList<>());
    }

    @Override
    public BookDTO findById(Integer id) {
        Book book = bookEntityService.findById(id);
        return dtoService.toFullDTO(book, loanEntityService.findByBookId(id));
    }

    @Override
    public List<BookDTO> findAll() {
        return bookEntityService.findAll().stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO update(Integer id, BookDTO dto) {
        Book book = bookEntityService.update(id, dto);
        return dtoService.toFullDTO(book, loanEntityService.findByBookId(id));
    }

    @Override
    public boolean delete(Integer id) {
        return bookEntityService.delete(id);
    }
}
