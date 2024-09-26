package com.back_si2.back_si2.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.back_si2.back_si2.entities.Product;

public interface IProductDao extends CrudRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}
