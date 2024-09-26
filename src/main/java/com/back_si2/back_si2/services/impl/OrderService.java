package com.back_si2.back_si2.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back_si2.back_si2.dto.order.CreateOrderDto;
import com.back_si2.back_si2.entities.Order;
import com.back_si2.back_si2.entities.OrderDetail;
import com.back_si2.back_si2.entities.Product;
import com.back_si2.back_si2.entities.User;
import com.back_si2.back_si2.persistence.IOrderDao;
import com.back_si2.back_si2.persistence.IOrderDetailDao;
import com.back_si2.back_si2.persistence.IProductDao;
import com.back_si2.back_si2.persistence.IUserDao;
import com.back_si2.back_si2.services.IOrderService;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IProductDao productDao;
    @Autowired
    IOrderDetailDao orderDetailDao;
    @Autowired
    IOrderDao orderDao;
    @Autowired
    IUserDao userDao;

    @Override
    public Order findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void createOrder(CreateOrderDto orderDto) {
        User user = userDao.findById(Long.parseLong(orderDto.getUserId())).orElseThrow();
        Order newOrder = new Order();
        newOrder.setUser(user);

        Order savedOrder = orderDao.save(newOrder);
        final double[] total = { 0 }; // Para modificar en el map deben ser final, es decir no pueden modificarse.
                                      // Usamos una ED inmutable.

        List<OrderDetail> items = orderDto.getOrder().stream().map(dto -> {
            Product product = productDao.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product Not Fount"));

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setPrice(product.getPrice()); // Precio actual del producto
            orderDetail.setQuantity(dto.getQuantity());
            orderDetail.setOrder(savedOrder);
            orderDetail.setProduct(product);
            total[0] += product.getPrice() * dto.getQuantity();

            return orderDetail;
        }).collect(Collectors.toList());

        savedOrder.setTotal(total[0]);
        orderDao.save(savedOrder);

        orderDetailDao.saveAll(items);
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
