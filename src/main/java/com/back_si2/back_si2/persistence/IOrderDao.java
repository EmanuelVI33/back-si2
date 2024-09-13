package com.back_si2.back_si2.persistence;

import org.springframework.data.repository.CrudRepository;
import com.back_si2.back_si2.entities.Order;

public interface IOrderDao extends CrudRepository<Order, Long> {
}
