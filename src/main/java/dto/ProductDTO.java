package dto;

import lombok.*;
import model.Category;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    private String name;
    private Collection<CategoryDTO> categories;
}
