package dto;

import lombok.*;
import model.BaseEntity;
import model.User;

import java.util.Collection;

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
