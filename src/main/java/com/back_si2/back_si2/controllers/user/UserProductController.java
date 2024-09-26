package com.back_si2.back_si2.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_si2.back_si2.dto.ApiResponse;
import com.back_si2.back_si2.entities.Product;
import com.back_si2.back_si2.services.IProductService;

@RestController
@RequestMapping("/products")
public class UserProductController {
    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Product>>> getAll() {
        ApiResponse<List<Product>> response = productService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
