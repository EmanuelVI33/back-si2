package com.back_si2.back_si2.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back_si2.back_si2.dto.ApiResponse;
import com.back_si2.back_si2.entities.Category;
import com.back_si2.back_si2.persistence.ICategoryDao;
import com.back_si2.back_si2.services.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryDao.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<Category>> findAll() {
        try {
            List<Category> categories = (List<Category>) categoryDao.findAll();
            return ApiResponse.<List<Category>>builder()
                    .data(categories).build();
        } catch (Exception e) {
            return ApiResponse.<List<Category>>builder()
                    .status(500)
                    .success(false)
                    .message("Error interno del servidor: " + e.getMessage())
                    .build();
        }

    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryDao.deleteById(id);
    }
}
