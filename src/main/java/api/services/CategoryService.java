package api.services;

import dto.CategoryDTO;
import exceptions.NoRecordException;
import model.Category;

import java.util.Collection;

public interface CategoryService {
    CategoryDTO get(Long id) throws NoRecordException;
    CategoryDTO create(Category entity);
    Collection<CategoryDTO> getAll();
    void delete(Long id) throws NoRecordException;
    void save();
}
