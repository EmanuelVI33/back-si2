package com.back_si2.back_si2.services;

import java.util.List;

import com.back_si2.back_si2.dto.ApiResponse;
import com.back_si2.back_si2.dto.product.CreateProductDto;
import com.back_si2.back_si2.entities.Product;

public interface IProductService {
    Product findById(Long id);

    ApiResponse<List<Product>> findAll();

    ApiResponse<List<Product>> findByCategory(Long id);

    void save(Product product);

    void deleteById(Long id);

    Product dtoToEntity(CreateProductDto productDto);
}
