package com.back_si2.back_si2.models.dto.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private Float price;
    private String description;
    private String categoryId;
    private String imageUrl;
}
