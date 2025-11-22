package com.microservice.order_service.business.services.abstracts;

import com.microservice.order_service.business.dtos.requests.OrderRequest;
import com.microservice.order_service.business.dtos.responses.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    List<OrderResponse> getOrders(String customerNumber);
}
