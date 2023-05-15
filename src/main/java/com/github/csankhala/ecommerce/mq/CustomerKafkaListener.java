package com.github.csankhala.ecommerce.mq;

import com.github.csankhala.ecommerce.dto.ShoppingCartDto;
import com.github.csankhala.ecommerce.service.ShoppingCartProcessor;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class CustomerKafkaListener {
    
    private final ShoppingCartProcessor shoppingCartProcessor;

    public CustomerKafkaListener(ShoppingCartProcessor shoppingCartProcessor) {
        this.shoppingCartProcessor = shoppingCartProcessor;
    }

    @Topic("cart-checkout")
    public void subscribeToCheckout(@KafkaKey Long customerId, ShoppingCartDto shoppingCart) {
        log.info("Received event from 'cart-checkout' with customerId: {}, shoppingCart: {}", customerId, shoppingCart);
        this.shoppingCartProcessor.processCart(customerId, shoppingCart);
    }
}