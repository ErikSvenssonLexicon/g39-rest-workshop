package com.example.g39restworkshop.controller;

import com.example.g39restworkshop.model.dto.BookDTO;
import com.example.g39restworkshop.service.interfaces.BookService;
import com.example.g39restworkshop.validation.groups.OnPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/api/v1/books")
    public ResponseEntity<List<BookDTO>> find(
            @RequestParam(value = "search", defaultValue = "all") String search,
            @RequestParam(value = "value", defaultValue = "") String value){

        switch (search.toLowerCase()){
            case "all":
                return ResponseEntity.ok(bookService.findAll());
            case "title":
                return ResponseEntity.ok(bookService.findByTitle(value.trim()));
            case "available":
                return ResponseEntity.ok(bookService.findByAvailable(Boolean.parseBoolean(value.trim())));
            case "reserved":
                return ResponseEntity.ok(bookService.findByReserved(Boolean.parseBoolean(value.trim())));
            default:
                throw new IllegalStateException("Invalid search type: '" + search + "'. Valid types are 'all', 'title', 'available' and 'reserved'.");
        }
    }

    @PostMapping("/api/v1/books")
    public ResponseEntity<BookDTO> create(@Validated(OnPost.class) @RequestBody BookDTO dto){
        return ResponseEntity.status(201).body(bookService.create(dto));
    }

    @GetMapping("/api/v1/books/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PutMapping("/api/v1/books/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") Integer id, @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.update(id, bookDTO));
    }

    @DeleteMapping("/api/v1/books/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(bookService.delete(id));
    }
}
