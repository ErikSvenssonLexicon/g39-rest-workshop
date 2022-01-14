package com.example.g39restworkshop.service.implementations;

import com.example.g39restworkshop.model.dto.LoanDTO;
import com.example.g39restworkshop.service.conversion.DTOService;
import com.example.g39restworkshop.service.interfaces.LoanEntityService;
import com.example.g39restworkshop.service.interfaces.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanEntityService loanEntityService;
    private final DTOService dtoService;

    @Override
    public LoanDTO create(LoanDTO dto) {
        return dtoService.toFullDTO(
                loanEntityService.create(dto)
        );
    }

    @Override
    public LoanDTO findById(Integer id) {
        return dtoService.toFullDTO(
                loanEntityService.findById(id)
        );
    }

    @Override
    public List<LoanDTO> findAll() {
        return loanEntityService.findAll().stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDTO update(Integer id, LoanDTO dto) {
        return dtoService.toFullDTO(
                loanEntityService.update(id, dto)
        );
    }

    @Override
    public boolean delete(Integer id) {
        return loanEntityService.delete(id);
    }

    @Override
    public List<LoanDTO> findByBookId(Integer bookId) {
        return loanEntityService.findByBookId(bookId).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDTO> findByUserId(Integer userId) {
        return loanEntityService.findByUserId(userId).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDTO> findByConcluded(boolean concluded) {
        return loanEntityService.findByConcluded(concluded).stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanDTO concludeLoan(Integer id, Integer userId) {
        return dtoService.toFullDTO(
                loanEntityService.concludeLoan(id, userId)
        );
    }

    @Override
    public LoanDTO extendLoan(Integer id, Integer userId, Integer days) {
        return dtoService.toFullDTO(
                loanEntityService.extendLoan(id, userId, days)
        );
    }
}
