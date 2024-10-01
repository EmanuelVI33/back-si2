package com.back_si2.back_si2.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_si2.back_si2.entities.Category;
import com.back_si2.back_si2.exception.BadRequestException;
import com.back_si2.back_si2.exception.ResourceNotFoundException;
import com.back_si2.back_si2.models.dto.category.CategoryDto;
import com.back_si2.back_si2.models.payloads.ResponseMessage;
import com.back_si2.back_si2.services.ICategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> showAll() {
        List<Category> categories = categoryService.findAll();
        if (categories == null || categories.size() < 1) {
            throw new ResourceNotFoundException("categories");
        }
        return new ResponseEntity<>(ResponseMessage.builder().data(categories).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryDto categoryDto) {
        try {
            System.out.println("Ingresa peticion " + categoryDto);
            Category category = categoryService.save(categoryDto);
            return new ResponseEntity<>(ResponseMessage.builder().data(category).build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(ResponseMessage.builder().data(category).build(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
        System.out.println("Ingresa a editar " + categoryDto);
        try {
            if (categoryService.existsById(id)) {
                Category category = null;
                categoryDto.setId(id);
                category = categoryService.save(categoryDto);
                return new ResponseEntity<>(
                        ResponseMessage.builder().data(CategoryDto.builder().id(category.getId())
                                .name(category.getName()).description(category.getDescription())
                                .imageUrl(categoryDto.getImageUrl()).build()).build(),
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
            Category categoryDelete = categoryService.findById(id);
            System.out.println("Category: " + categoryDelete);
            categoryService.delete(categoryDelete);
            return new ResponseEntity<>(ResponseMessage.builder().data(categoryDelete).build(),
                    HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
