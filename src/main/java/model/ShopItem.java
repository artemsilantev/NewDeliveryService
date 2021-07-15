package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopItem extends BaseEntity{
    private Shop shop;
    private Product product;
    private Double price;
    private Integer amount;
}
