package com.microservice.order_service.business.services.concretes;

import com.microservice.order_service.business.mappers.OrderMapper;
import com.microservice.order_service.business.services.abstracts.OrderMessageService;
import com.microservice.order_service.entities.Order;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderMessageManager implements OrderMessageService {

    @Value("${sqs.url}")
    private String sqsUrl;

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final OrderMapper mapper;

    public OrderMessageManager(QueueMessagingTemplate queueMessagingTemplate, OrderMapper mapper) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.mapper = mapper;
    }

    @Override
    public void sendMessage(Order order) {
        queueMessagingTemplate.convertAndSend(sqsUrl, mapper.toResponse(order));
    }
}
