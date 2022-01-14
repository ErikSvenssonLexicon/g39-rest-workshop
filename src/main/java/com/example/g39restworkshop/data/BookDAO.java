package com.example.g39restworkshop.data;

import com.example.g39restworkshop.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.reserved = :reserved")
    List<Book> findByReservedStatus(@Param("reserved") boolean reserved);

    @Query("SELECT b FROM Book b WHERE b.available = :available")
    List<Book> findByAvailableStatus(@Param("available") boolean available);

    @Query("SELECT b FROM Book b WHERE UPPER(b.title) = UPPER(:title)")
    List<Book> findByTitle(@Param("title") String title);
}
