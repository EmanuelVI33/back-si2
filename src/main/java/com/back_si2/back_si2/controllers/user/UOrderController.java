package com.back_si2.back_si2.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back_si2.back_si2.dto.ApiResponse;
import com.back_si2.back_si2.dto.order.CreateOrderDto;
import com.back_si2.back_si2.services.IOrderService;

@RestController
@RequestMapping("/orders")
public class UOrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createOrder(@RequestBody CreateOrderDto createOrder) {
        System.out.println("Order: " + createOrder);
        orderService.createOrder(createOrder);
        return ResponseEntity.status(201).build();
    }

}
