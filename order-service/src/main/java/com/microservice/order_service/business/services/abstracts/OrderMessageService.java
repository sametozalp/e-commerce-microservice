package com.microservice.order_service.business.services.abstracts;

import com.microservice.order_service.entities.Order;

public interface OrderMessageService {

    void sendMessage(Order order);

}
