package services;

import dto.CategoryDTO;

import model.Category;

import java.util.Collection;


public interface CategoryService extends AbstractService<CategoryDTO, Category> {
    Collection<CategoryDTO> getParents(Long id);
}
