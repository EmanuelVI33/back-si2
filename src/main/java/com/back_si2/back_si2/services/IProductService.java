package com.back_si2.back_si2.services;

import java.util.List;

import com.back_si2.back_si2.entities.Product;
import com.back_si2.back_si2.models.dto.product.ProductDto;

public interface IProductService {
    Product findById(Long id);

    List<Product> findAll();

    List<Product> findByCategory(Long id);

    Product save(ProductDto productDto);

    void delete(Product id);

    boolean existsById(Long id);
}
