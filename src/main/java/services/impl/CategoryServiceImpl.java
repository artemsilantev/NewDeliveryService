package services.impl;

import mappers.Mapper;
import repositories.CategoryRepository;
import dto.CategoryDTO;
import model.Category;
import services.CategoryService;

public class CategoryServiceImpl extends AbstractServiceImpl<CategoryDTO, Category>
        implements CategoryService {

    public CategoryServiceImpl(Mapper<CategoryDTO, Category> mapper, CategoryRepository categoryRepository) {
        super(mapper, categoryRepository);
    }

    @Override
    public CategoryDTO create(Category entity) {
        for(Category category: abstractRepository.getAll()){
            if(category.getName().equals(entity.getName())) {
                entity.setId(category.getId());
                return mapperDTO.toTarget(entity);
            }
        }
        entity.setId(abstractRepository.create(entity).getId());
        return mapperDTO.toTarget(entity);
    }

}
