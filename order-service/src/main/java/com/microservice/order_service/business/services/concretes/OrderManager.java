package com.microservice.order_service.business.services.concretes;

import com.microservice.order_service.business.dtos.requests.OrderRequest;
import com.microservice.order_service.business.dtos.responses.OrderResponse;
import com.microservice.order_service.business.mappers.OrderMapper;
import com.microservice.order_service.business.services.abstracts.OrderMessageService;
import com.microservice.order_service.business.services.abstracts.OrderService;
import com.microservice.order_service.entities.Order;
import com.microservice.order_service.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {

    private final OrderMapper mapper;
    private final OrderRepository repository;
    private final OrderMessageService orderMessageService;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = mapper.toEntity(request);
        order.setDate(LocalDateTime.now());
        Order saved = repository.save(order);
        orderMessageService.sendMessage(saved);
        return mapper.toResponse(saved);
    }

    @Override
    public List<OrderResponse> getOrders(String customerNumber) {
        return repository.findByCustomerNumber(customerNumber)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}
