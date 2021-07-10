package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{
    private String name;
    private Set<Category> categories;

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }
}
