package com.back_si2.back_si2.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back_si2.back_si2.entities.Category;
import com.back_si2.back_si2.exception.ResourceNotFoundException;
import com.back_si2.back_si2.models.dto.category.CategoryDto;
import com.back_si2.back_si2.persistence.ICategoryDao;
import com.back_si2.back_si2.services.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("category"));
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = (List<Category>) categoryDao.findAll();
        return categories;
    }

    @Override
    @Transactional
    public Category save(CategoryDto categoryDto) {
        var category = Category.builder().id(categoryDto.getId()).name(categoryDto.getName())
                .description(categoryDto.getDescription()).build();
        return categoryDao.save(category);
    }

    @Override
    @Transactional
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryDao.existsById(id);
    }
}
