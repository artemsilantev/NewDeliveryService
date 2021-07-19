package model;

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
@ToString
public class Order extends BaseEntity {

  private User user;
  private Collection<ShopItem> items;
  private Double totalCost;
}
