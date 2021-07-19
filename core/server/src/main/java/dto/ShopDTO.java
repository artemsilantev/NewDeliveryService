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
public class ShopDTO {

  private Long id;
  private String name;
  private String address;
  private String email;
}
