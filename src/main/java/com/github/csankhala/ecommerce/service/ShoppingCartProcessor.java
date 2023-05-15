package com.github.csankhala.ecommerce.service;

import com.github.csankhala.ecommerce.client.ProductCatalogClient;
import com.github.csankhala.ecommerce.dto.OrderDto;
import com.github.csankhala.ecommerce.dto.ProductDto;
import com.github.csankhala.ecommerce.dto.ShoppingCartDto;
import com.github.csankhala.ecommerce.mq.OrderKafkaClient;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class ShoppingCartProcessor {
  private final OrderService orderService;
  private final OrderKafkaClient mqclient;

  private final ProductCatalogClient productCatalogClient;

  public ShoppingCartProcessor(
      OrderService orderService,
      OrderKafkaClient mqclient,
      ProductCatalogClient productCatalogClient) {
    this.orderService = orderService;
    this.mqclient = mqclient;
    this.productCatalogClient = productCatalogClient;
  }

  public void processCart(long customerId, ShoppingCartDto shoppingCartDto) {

    List<ProductDto> products =
        shoppingCartDto.getProducts().stream()
            .map(this.productCatalogClient::getProduct)
            .toList();

    OrderDto order = OrderDto.builder().customerId(customerId).products(products).build();

    // TODO payment processing

    //TODO order creation in db
    log.info("Sending event 'order-created' with customerId: {}, order:{}", customerId, order);
    this.mqclient.sendOrderCreated(customerId, order);
    // TODO payment fail implementation

  }
}
