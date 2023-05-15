package com.github.csankhala.ecommerce.dto;

import io.micronaut.serde.annotation.Serdeable;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Serdeable
@Data
@Builder
public class OrderDto {
  private long id;
  private long customerId;
  private List<ProductDto> products;
}
