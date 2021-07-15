package model;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collection;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseEntity{
    private String name;
    private Collection<Category> categories;
}
