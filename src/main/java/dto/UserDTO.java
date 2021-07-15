package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String firstName;
    private String secondName;
    private String number;
    private String email;
    private String address;
}
