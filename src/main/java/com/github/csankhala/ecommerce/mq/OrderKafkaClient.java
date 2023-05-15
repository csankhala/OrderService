package com.github.csankhala.ecommerce.mq;


import com.github.csankhala.ecommerce.dto.OrderDto;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface OrderKafkaClient {

    @Topic("order-created")
    void sendOrderCreated(@KafkaKey long customerId, OrderDto order);
}