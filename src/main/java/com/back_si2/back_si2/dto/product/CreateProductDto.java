package com.back_si2.back_si2.dto.product;

import lombok.Data;

@Data
public class CreateProductDto {
    private String name;
    private Float price;
    private String description;
    private String categoryId;
}
