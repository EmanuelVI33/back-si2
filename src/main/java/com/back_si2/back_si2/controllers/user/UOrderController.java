package com.back_si2.back_si2.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_si2.back_si2.entities.Order;
import com.back_si2.back_si2.models.dto.order.CreateOrderDto;
import com.back_si2.back_si2.models.payloads.ApiResponseV1;
import com.back_si2.back_si2.services.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/orders")
public class UOrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponseV1<Long>> createOrder(@RequestBody CreateOrderDto createOrder) {
        System.out.println("Order: " + createOrder);
        ApiResponseV1<Long> response = orderService.createOrder(createOrder);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<ApiResponseV1<List<Order>>> getOrderById(@PathVariable Long id) {
        ApiResponseV1<List<Order>> response = orderService.findByUser(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
