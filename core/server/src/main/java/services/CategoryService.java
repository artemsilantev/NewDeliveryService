package services;

import dto.CategoryDTO;
import java.util.Collection;
import model.Category;


public interface CategoryService extends AbstractService<CategoryDTO, Category> {

  Collection<CategoryDTO> getParents(Long id);
}
