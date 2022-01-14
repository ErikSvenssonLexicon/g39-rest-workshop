package com.example.g39restworkshop.service.interfaces;

import com.example.g39restworkshop.model.dto.LibraryUserDTO;
import com.example.g39restworkshop.service.interfaces.GenericCRUD;

public interface LibraryUserService extends GenericCRUD<LibraryUserDTO, LibraryUserDTO, Integer> {
    LibraryUserDTO findByEmail(String email);
}
