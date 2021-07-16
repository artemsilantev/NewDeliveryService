package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class ShopDTO {
    private Long id;
    private String name;
    private String address;
    private String email;
}
