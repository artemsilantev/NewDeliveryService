package dto;

import lombok.*;

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
