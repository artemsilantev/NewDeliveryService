package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class UserDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private String number;
    private String email;
    private String address;
}
