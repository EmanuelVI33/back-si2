package com.back_si2.back_si2.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.back_si2.back_si2.entities.Order;

public interface IOrderDao extends CrudRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
