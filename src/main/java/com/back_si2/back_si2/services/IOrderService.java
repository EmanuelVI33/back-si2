package com.back_si2.back_si2.services;

import java.util.List;

import com.back_si2.back_si2.entities.Order;
import com.back_si2.back_si2.models.dto.order.CreateOrderDto;
import com.back_si2.back_si2.models.payloads.ApiResponseV1;

public interface IOrderService {
    Order findById(Long id);

    List<Order> findAll();

    ApiResponseV1<List<Order>> findByUser(Long userId);

    ApiResponseV1<Long> createOrder(CreateOrderDto order);

    void deleteById(Long id);
}
