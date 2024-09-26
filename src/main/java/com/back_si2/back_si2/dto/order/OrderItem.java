package com.back_si2.back_si2.dto.order;

import lombok.Data;

@Data
public class OrderItem {
    Long productId;
    Long quantity;
}