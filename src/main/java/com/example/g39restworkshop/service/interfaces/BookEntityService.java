package com.example.g39restworkshop.service.interfaces;

import com.example.g39restworkshop.model.dto.BookDTO;
import com.example.g39restworkshop.model.entity.Book;
import com.example.g39restworkshop.service.interfaces.GenericCRUD;

import java.util.List;

public interface BookEntityService extends GenericCRUD<Book, BookDTO, Integer> {
    List<Book> findByReserved(boolean reserved);
    List<Book> findByAvailable(boolean available);
    List<Book> findByTitle(String title);
}
