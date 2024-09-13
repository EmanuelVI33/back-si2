package com.back_si2.back_si2.services;

import java.util.List;

import com.back_si2.back_si2.entities.Order;

public interface IOrderService {
    Order findById(Long id);

    List<Order> findAll();

    void save(Order order);

    void deleteById(Long id);
}
