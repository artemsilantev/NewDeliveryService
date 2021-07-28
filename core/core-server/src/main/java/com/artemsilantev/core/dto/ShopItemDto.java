package com.artemsilantev.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class ShopItemDto {

  private Long id;
  private ShopDto shop;
  private ProductDto product;
  private Double price;
  private Integer amount;
}
