package com.back_si2.back_si2.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_si2.back_si2.entities.Product;
import com.back_si2.back_si2.models.payloads.ResponseMessage;
import com.back_si2.back_si2.services.IProductService;

@RestController
@RequestMapping("/products")
public class UserProductController {
    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<Product> product = productService.findAll();
        return new ResponseEntity<>(ResponseMessage.builder().data(product).build(), HttpStatus.OK);
    }
}
