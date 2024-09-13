package com.back_si2.back_si2.persistence;

import org.springframework.data.repository.CrudRepository;
import com.back_si2.back_si2.entities.OrderDetail;

public interface IOrderDetailDao extends CrudRepository<OrderDetail, Long> {
}
