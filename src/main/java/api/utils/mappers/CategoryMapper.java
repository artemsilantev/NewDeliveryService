package api.utils.mappers;

import dto.CategoryDTO;
import model.Category;

import java.util.Collection;

public interface CategoryMapper {
    CategoryDTO map(Category category);
    Collection<CategoryDTO> mapCollection(Collection<Category> collection);
}
