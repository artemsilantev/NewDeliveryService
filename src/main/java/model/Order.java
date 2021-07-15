package model;

import lombok.*;

import java.util.Collection;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order extends BaseEntity{
    private User user;
    private Collection<ShopItem> items;
    private Double totalCost;
}
