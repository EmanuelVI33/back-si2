package com.back_si2.back_si2.services;

import java.util.List;

import com.back_si2.back_si2.entities.Category;

public interface ICategoryService {
    Category findById(Long id);

    List<Category> findAll();

    void save(Category category);

    void deleteById(Long id);
}
