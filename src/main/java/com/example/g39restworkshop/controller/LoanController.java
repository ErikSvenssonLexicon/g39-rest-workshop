package com.example.g39restworkshop.controller;

import com.example.g39restworkshop.model.dto.LoanDTO;
import com.example.g39restworkshop.service.interfaces.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/api/v1/loans")
    public ResponseEntity<List<LoanDTO>> find(@RequestParam(name = "search", defaultValue = "all") String search,
                                              @RequestParam(name = "value", defaultValue = "") String value){
        List<LoanDTO> result;
        switch (search.toLowerCase()){
            case "all":
                result = loanService.findAll();
                break;
            case "user":
                result = loanService.findByUserId(Integer.valueOf(value));
                break;
            case "book":
                result = loanService.findByBookId(Integer.valueOf(value));
                break;
            case "concluded":
                result = loanService.findByConcluded(Boolean.parseBoolean(value));
                break;
            default:
                throw new IllegalArgumentException("Invalid search type: '" + search + "'. Valid types are 'all', 'user', 'book' and 'concluded'.");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/v1/loans")
    public ResponseEntity<LoanDTO> create(@RequestBody LoanDTO dto){
        return ResponseEntity.status(201).body(loanService.create(dto));
    }

    @GetMapping("/api/v1/loans/{id}")
    public ResponseEntity<LoanDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(loanService.findById(id));
    }

    @PutMapping("/api/v1/loans/{id}")
    public ResponseEntity<LoanDTO> update(@PathVariable("id") Integer id, @RequestBody LoanDTO dto){
        return ResponseEntity.ok(loanService.update(id, dto));
    }

    @PutMapping("/api/v1/loans/{id}/extend")
    public ResponseEntity<LoanDTO> extendLoan(@PathVariable("id") Integer id, @RequestParam("userId") Integer userId, @RequestParam Integer days){
        return ResponseEntity.ok(loanService.extendLoan(id, userId, days));
    }

    @PutMapping("/api/v1/loans/{id}/return")
    public ResponseEntity<LoanDTO> terminateLoan(@PathVariable("id") Integer id, @RequestParam("userId") Integer userId){
        return ResponseEntity.ok(loanService.concludeLoan(id, userId));
    }

    @DeleteMapping("/api/v1/loans/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(loanService.delete(id));
    }
}
