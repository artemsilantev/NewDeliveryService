package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopItemDTO {
    private ShopDTO shop;
    private ProductDTO product;
    private Double price;
    private Integer amount;
}
