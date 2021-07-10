package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Category;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private Set<Category> categories;


    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }
}
