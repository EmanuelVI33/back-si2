package com.back_si2.back_si2.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.back_si2.back_si2.entities.Product;
import com.back_si2.back_si2.exception.BadRequestException;
import com.back_si2.back_si2.exception.ResourceNotFoundException;
import com.back_si2.back_si2.models.dto.product.ProductDto;
import com.back_si2.back_si2.models.payloads.ResponseMessage;
import com.back_si2.back_si2.services.IProductService;

@RestController
@RequestMapping("/admin/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<?> showAll() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(ResponseMessage.builder().data(products).build(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> showAllByCategory(@PathVariable Long id) {
        List<Product> products = productService.findByCategory(id);
        return new ResponseEntity<>(ResponseMessage.builder().data(products).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return new ResponseEntity<>(ResponseMessage.builder().data(product).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDto productDto) {
        try {
            Product product = productService.save(productDto);
            return new ResponseEntity<>(ResponseMessage.builder().data(product).build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        System.out.println("Datos eviados " + id + "   " + productDto);
        try {
            if (productService.existsById(id)) {
                Product product = null;
                product = productService.findById(id);
                // productDto.setId(id);
                // if (productDto.getImageUrl() == null) {
                // productDto.setImageUrl(product.getImageUrl());
                // }
                product = productService.save(productDto);
                return new ResponseEntity<>(
                        ResponseMessage.builder().data(ProductDto.builder().id(product.getId())
                                .name(product.getName()).description(product.getDescription())
                                .categoryId(productDto.getCategoryId()).build()).build(),
                        HttpStatus.CREATED);
            } else {
                throw new ResourceNotFoundException("category", "id", id);
            }
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Product productDelete = productService.findById(id);
            productService.delete(productDelete);
            return new ResponseEntity<>(ResponseMessage.builder().data(productDelete).build(),
                    HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
