package com.back_si2.back_si2.services;

import java.util.List;

import com.back_si2.back_si2.entities.Category;
import com.back_si2.back_si2.models.dto.category.CategoryDto;

public interface ICategoryService {
    List<Category> findAll();

    Category save(CategoryDto category);

    Category findById(Long id);

    void delete(Category id);

    boolean existsById(Long id);
}
