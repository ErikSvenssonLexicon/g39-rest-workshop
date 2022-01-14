package com.example.g39restworkshop.service.interfaces;

import com.example.g39restworkshop.model.dto.LibraryUserDTO;
import com.example.g39restworkshop.model.entity.LibraryUser;
import com.example.g39restworkshop.service.interfaces.GenericCRUD;

public interface LibraryUserEntityService extends GenericCRUD<LibraryUser, LibraryUserDTO, Integer> {
    LibraryUser findByEmail(String email);
}
