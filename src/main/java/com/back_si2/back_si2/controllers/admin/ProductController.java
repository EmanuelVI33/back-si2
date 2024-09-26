package com.back_si2.back_si2.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_si2.back_si2.dto.ApiResponse;
import com.back_si2.back_si2.dto.product.CreateProductDto;
import com.back_si2.back_si2.dto.product.UpdateProductDto;
import com.back_si2.back_si2.entities.Product;
import com.back_si2.back_si2.services.IProductService;

@RestController
@RequestMapping("/admin/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {
        ApiResponse<List<Product>> response = productService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse<List<Product>>> getAllWithCategory(@PathVariable Long id) {
        ApiResponse<List<Product>> response = productService.findByCategory(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product category = productService.findById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateProductDto createProductDto) {
        Product product = productService.dtoToEntity(createProductDto);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateProductDto updateProductDto) {
        Product existingProduct = productService.findById(id);
        if (existingProduct != null) {
            updateProductDto.setId(id);
            Product product = productService.dtoToEntity(updateProductDto);
            product.setId(id);
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
