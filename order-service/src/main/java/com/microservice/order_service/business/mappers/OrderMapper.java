package com.microservice.order_service.business.mappers;

import com.microservice.order_service.business.dtos.requests.OrderRequest;
import com.microservice.order_service.business.dtos.responses.OrderResponse;
import com.microservice.order_service.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toResponse(Order order);

    Order toEntity(OrderRequest request);
}
