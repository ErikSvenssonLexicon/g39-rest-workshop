package com.example.g39restworkshop.service.implementations;

import com.example.g39restworkshop.model.dto.LibraryUserDTO;
import com.example.g39restworkshop.model.entity.LibraryUser;
import com.example.g39restworkshop.model.entity.Loan;
import com.example.g39restworkshop.service.conversion.DTOService;
import com.example.g39restworkshop.service.interfaces.LibraryUserEntityService;
import com.example.g39restworkshop.service.interfaces.LibraryUserService;
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
public class LibraryUserServiceImpl implements LibraryUserService {

    private final LibraryUserEntityService libraryUserEntityService;
    private final LoanEntityService loanEntityService;
    private final DTOService dtoService;

    @Override
    public LibraryUserDTO create(LibraryUserDTO dto) {
        return dtoService.toFullDTO(
                libraryUserEntityService.create(dto),
                new ArrayList<>()
        );
    }

    @Override
    public LibraryUserDTO findById(Integer id) {
        return dtoService.toFullDTO(
                libraryUserEntityService.findById(id),
                loanEntityService.findByUserId(id)
        );
    }

    @Override
    public List<LibraryUserDTO> findAll() {
        return libraryUserEntityService.findAll().stream()
                .map(dtoService::toSmallDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LibraryUserDTO update(Integer id, LibraryUserDTO dto) {
        return dtoService.toFullDTO(
                libraryUserEntityService.update(id, dto),
                loanEntityService.findByUserId(id)
        );
    }

    @Override
    public boolean delete(Integer id) {
        return libraryUserEntityService.delete(id);
    }

    @Override
    public LibraryUserDTO findByEmail(String email) {
        LibraryUser user = libraryUserEntityService.findByEmail(email);
        List<Loan> loans = loanEntityService.findByUserId(user.getId());
        return dtoService.toFullDTO(
                user,
                loans
        );
    }
}
