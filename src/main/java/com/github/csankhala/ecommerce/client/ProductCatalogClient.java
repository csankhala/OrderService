package com.github.csankhala.ecommerce.client;

import com.github.csankhala.ecommerce.dto.ProductDto;
import jakarta.inject.Singleton;
import java.util.Random;
import java.util.UUID;

@Singleton
public class ProductCatalogClient {

	public ProductDto getProduct(long productId) {
		//TODO validate through product catalog service
		return ProductDto.builder().id(productId)
			.name(UUID.randomUUID().toString())
			.price(new Random().nextInt()).build();
	}
}
