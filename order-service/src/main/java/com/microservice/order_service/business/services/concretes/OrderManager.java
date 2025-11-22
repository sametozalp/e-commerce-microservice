package com.microservice.order_service.business.services.concretes;

import com.microservice.order_service.business.dtos.requests.OrderRequest;
import com.microservice.order_service.business.dtos.responses.OrderResponse;
import com.microservice.order_service.business.mappers.OrderMapper;
import com.microservice.order_service.repositories.OrderRepository;
import com.microservice.order_service.business.services.abstracts.OrderService;
import com.microservice.order_service.entities.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {

    private final OrderMapper mapper;
    private final OrderRepository repository;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = mapper.toEntity(request);
        order.setDate(new Date());
        return mapper.toResponse(repository.save(order));
    }

    @Override
    public List<OrderResponse> getOrders(String customerNumber) {
        return repository.findByCustomerNumber()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
