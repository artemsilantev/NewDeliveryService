package dto;

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
public class ShopItemDTO {

  private Long id;
  private ShopDTO shop;
  private ProductDTO product;
  private Double price;
  private Integer amount;
}
