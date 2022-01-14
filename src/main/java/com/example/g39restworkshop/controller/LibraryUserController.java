package com.example.g39restworkshop.controller;

import com.example.g39restworkshop.model.dto.LibraryUserDTO;
import com.example.g39restworkshop.service.interfaces.LibraryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LibraryUserController {
    private final LibraryUserService libraryUserService;

    @GetMapping("/api/v1/users")
    public ResponseEntity<?> find(@RequestParam(value = "email", required = false) String email){
        if(email == null){
            return ResponseEntity.ok(libraryUserService.findAll());
        }
        return ResponseEntity.ok(libraryUserService.findByEmail(email));
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<LibraryUserDTO> create(@RequestBody LibraryUserDTO dto){
        return ResponseEntity.status(201).body(libraryUserService.create(dto));
    }

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<LibraryUserDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(libraryUserService.findById(id));
    }

    @PutMapping("/api/v1/users/{id}")
    public ResponseEntity<LibraryUserDTO> update(@PathVariable("id") Integer id, @RequestBody LibraryUserDTO dto){
        return ResponseEntity.ok(libraryUserService.update(id, dto));
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(libraryUserService.delete(id));
    }
}
