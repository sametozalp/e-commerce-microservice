package com.microservice.order_service.controllers;

import com.microservice.order_service.business.dtos.requests.OrderRequest;
import com.microservice.order_service.business.services.abstracts.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    ResponseEntity<?> getOrders(@RequestParam String customerNumber) {
        return ResponseEntity.ok(orderService.getOrders(customerNumber));
    }
}
