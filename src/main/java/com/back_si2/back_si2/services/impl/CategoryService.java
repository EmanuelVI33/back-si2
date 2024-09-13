package com.back_si2.back_si2.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Category> findAll() {
        return (List<Category>) categoryDao.findAll();
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
