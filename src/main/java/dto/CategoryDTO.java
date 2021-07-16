package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
}
