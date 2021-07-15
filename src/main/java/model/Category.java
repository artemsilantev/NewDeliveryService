package model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category extends BaseEntity {
    private String name;
    private String description;
    private Category parent;


}
