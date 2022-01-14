package com.example.g39restworkshop.service.interfaces;

import com.example.g39restworkshop.model.dto.BookDTO;
import com.example.g39restworkshop.service.interfaces.GenericCRUD;

import java.util.List;

public interface BookService extends GenericCRUD<BookDTO, BookDTO, Integer> {
    List<BookDTO> findByReserved(boolean reserved);
    List<BookDTO> findByAvailable(boolean available);
    List<BookDTO> findByTitle(String title);
}
