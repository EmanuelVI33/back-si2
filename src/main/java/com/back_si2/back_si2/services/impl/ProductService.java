package com.back_si2.back_si2.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back_si2.back_si2.entities.Product;
import com.back_si2.back_si2.models.dto.product.ProductDto;
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
    public List<Product> findAll() {
        List<Product> products = (List<Product>) productDao.findAll();
        return products;
    }

    @Override
    @Transactional
    public Product save(ProductDto productDto) {
        var category = categoryService.findById(Long.parseLong(productDto.getCategoryId()));
        var product = Product.builder().id(productDto.getId()).name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice()).imageUrl(productDto.getImageUrl()).category(category).build();
        return productDao.save(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productDao.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategory(Long id) {
        List<Product> products = (List<Product>) productDao.findByCategoryId(id);
        return products;
    }

    @Override
    public boolean existsById(Long id) {
        return productDao.existsById(id);
    }

}
