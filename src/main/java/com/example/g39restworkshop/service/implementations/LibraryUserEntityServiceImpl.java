package com.example.g39restworkshop.service.implementations;

import com.example.g39restworkshop.data.LibraryUserDAO;
import com.example.g39restworkshop.exception.AppEntityNotFoundException;
import com.example.g39restworkshop.model.dto.LibraryUserDTO;
import com.example.g39restworkshop.model.entity.LibraryUser;
import com.example.g39restworkshop.service.interfaces.LibraryUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LibraryUserEntityServiceImpl implements LibraryUserEntityService {

    private final LibraryUserDAO libraryUserDAO;

    @Override
    public LibraryUser create(LibraryUserDTO dto) {
        if(dto == null) throw new IllegalArgumentException("Dto was null");
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setName(dto.getName().trim());
        libraryUser.setEmail(dto.getEmail().trim());

        return libraryUserDAO.save(libraryUser);
    }

    @Override
    public LibraryUser findById(Integer id) {
        return libraryUserDAO.findById(id)
                .orElseThrow(() -> new AppEntityNotFoundException("Could not find user with id " + id));
    }

    @Override
    public List<LibraryUser> findAll() {
        return libraryUserDAO.findAll();
    }

    @Override
    public LibraryUser update(Integer id, LibraryUserDTO dto) {
        if(dto == null) throw new IllegalArgumentException("Dto was null");
        LibraryUser libraryUser = findById(id);
        Optional<LibraryUser> optional = libraryUserDAO.findByEmail(dto.getEmail().trim());
        if(optional.isPresent() && !id.equals(optional.get().getId())){
            throw new IllegalArgumentException("Email is already in use");
        }

        libraryUser.setName(dto.getName().trim());
        libraryUser.setEmail(dto.getEmail().trim());

        return libraryUserDAO.save(libraryUser);
    }

    @Override
    public boolean delete(Integer id) {
        libraryUserDAO.deleteById(id);
        return !libraryUserDAO.existsById(id);
    }

    @Override
    public LibraryUser findByEmail(String email) {
        return libraryUserDAO.findByEmail(email.trim())
                .orElseThrow(() -> new AppEntityNotFoundException("Could not find user with provided email"));
    }
}
