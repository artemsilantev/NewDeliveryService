package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shop extends BaseEntity{
    private String name;
    private String address;
    private String email;
}
