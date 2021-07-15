package dto;

import lombok.*;
import model.BaseEntity;
import model.User;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO extends BaseEntity {
    private User user;
    private Collection<ShopItemDTO> items;
    private Double totalCost;
}
