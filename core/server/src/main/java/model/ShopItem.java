package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopItem extends BaseEntity {

  private Shop shop;
  private Product product;
  private Double price;
  private Integer amount;
}
