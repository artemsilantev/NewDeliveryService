package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity{
    private String firstName;
    private String secondName;
    private String number;
    private String email;
    private String address;
}
