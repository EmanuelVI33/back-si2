package com.back_si2.back_si2.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateProductDto extends CreateProductDto {
    private Long id;
}
