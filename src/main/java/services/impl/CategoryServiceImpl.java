package services.impl;

import mappers.Mapper;
import repositories.CategoryRepository;
import dto.CategoryDTO;
import model.Category;
import services.CategoryService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class CategoryServiceImpl extends AbstractServiceImpl<CategoryDTO, Category>
        implements CategoryService {

    public CategoryServiceImpl(Mapper<CategoryDTO, Category> mapper, CategoryRepository categoryRepository) {
        super(mapper, categoryRepository);
    }

    @Override
    public CategoryDTO create(Category entity) {
        for (Category category : abstractRepository.getAll()) {
            if (category.getName().equals(entity.getName())) {
                entity.setId(category.getId());
                return mapperDTO.toTarget(entity);
            }
        }
        entity.setId(abstractRepository.create(entity).getId());
        return mapperDTO.toTarget(entity);
    }

    @Override
    public Collection<CategoryDTO> getParents(Long id) {
        var categories = new ArrayList<Category>();
        var startCategory = abstractRepository.get(id);
        var categoryCurrent = startCategory;
        while (categoryCurrent.getParent() != null && categoryCurrent.getParent() != startCategory) {
            categories.add(categoryCurrent.getParent());
            categoryCurrent = categoryCurrent.getParent();
        }
        return categories.stream().map(mapperDTO::toTarget).collect(Collectors.toList());
    }
}
