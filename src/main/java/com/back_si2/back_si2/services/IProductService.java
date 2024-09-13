package com.back_si2.back_si2.services;

import java.util.List;

import com.back_si2.back_si2.entities.Product;

public interface IProductService {
    Product findById(Long id);

    List<Product> findAll();

    void save(Product product);

    void deleteById(Long id);
}
