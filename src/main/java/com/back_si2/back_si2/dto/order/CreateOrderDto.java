package com.back_si2.back_si2.dto.order;

import java.util.List;

import lombok.Data;

@Data
public class CreateOrderDto {
    String userId;
    List<OrderItem> order;
}
