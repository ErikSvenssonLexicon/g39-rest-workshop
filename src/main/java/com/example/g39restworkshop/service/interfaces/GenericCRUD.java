package com.example.g39restworkshop.service.interfaces;

import java.util.List;

public interface GenericCRUD <R, T, ID>{
    R create(T dto);
    R findById(ID id);
    List<R> findAll();
    R update(ID id, T dto);
    boolean delete(ID id);
}
