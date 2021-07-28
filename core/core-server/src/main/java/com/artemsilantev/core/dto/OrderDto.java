package com.artemsilantev.core.dto;

import java.util.Collection;
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
public class OrderDto {

  
  private Long id;
  private UserDto user;
  private Collection<ShopItemDto> items;
  private Double totalCost;
}
