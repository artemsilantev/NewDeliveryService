package com.artemsilantev.core.dto;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.artemsilantev.core.model.BaseEntity;
import com.artemsilantev.core.model.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class OrderDTO extends BaseEntity {

  private Long id;
  private User user;
  private Collection<ShopItemDTO> items;
  private Double totalCost;
}
