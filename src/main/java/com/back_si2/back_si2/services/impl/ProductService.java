package com.back_si2.back_si2.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back_si2.back_si2.dto.ApiResponse;
import com.back_si2.back_si2.dto.product.CreateProductDto;
import com.back_si2.back_si2.entities.Product;
import com.back_si2.back_si2.persistence.IProductDao;
import com.back_si2.back_si2.services.ICategoryService;
import com.back_si2.back_si2.services.IProductService;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductDao productDao;
    @Autowired
    ICategoryService categoryService;

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productDao.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<Product>> findAll() {
        try {
            List<Product> products = (List<Product>) productDao.findAll();
            return ApiResponse.<List<Product>>builder()
                    .data(products).build();
        } catch (Exception e) {
            return ApiResponse.<List<Product>>builder()
                    .status(500)
                    .success(false)
                    .message("Error interno del servidor: " + e.getMessage())
                    .build();
        }
    }

    @Override
    @Transactional
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Product dtoToEntity(CreateProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(categoryService.findById(Long.parseLong(productDto.getCategoryId())));
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<Product>> findByCategory(Long id) {
        try {
            List<Product> products = (List<Product>) productDao.findByCategoryId(id);
            return ApiResponse.<List<Product>>builder()
                    .data(products).build();
        } catch (Exception e) {
            return ApiResponse.<List<Product>>builder()
                    .status(500)
                    .success(false)
                    .message("Error interno del servidor: " + e.getMessage())
                    .build();
        }
    }

}
