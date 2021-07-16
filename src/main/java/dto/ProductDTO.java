package dto;

import lombok.*;
import model.Category;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class ProductDTO {
    private Long id;
    private String name;
    private Collection<CategoryDTO> categories;
}
