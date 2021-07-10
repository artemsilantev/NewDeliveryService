package services;

import api.utils.mappers.CategoryMapper;
import api.repositories.CategoryRepository;
import api.services.CategoryService;
import api.utils.savers.Saver;
import contexts.CategoryContext;
import dto.CategoryDTO;
import exceptions.NoRecordException;
import model.Category;

import java.util.Collection;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper mapper;
    private final Saver saver;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper, Saver saver) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.saver = saver;
    }

    @Override
    public CategoryDTO get(Long id) throws NoRecordException {
        return mapper.map(categoryRepository.get(id).orElseThrow(()->new NoRecordException("category",id)));
    }

    @Override
    public CategoryDTO create(Category entity) {
        for(Category category: categoryRepository.getAll()){
            if(category.getName().equals(entity.getName())) {
                entity.setId(category.getId());
                return mapper.map(entity);
            }
        }
        entity.setId(categoryRepository.create(entity).getId());
        return mapper.map(entity);
    }
    @Override
    public Collection<CategoryDTO> getAll() {
        return mapper.mapCollection(categoryRepository.getAll());
    }

    @Override
    public void save() {
        saver.save(categoryRepository.getAll(), CategoryContext.categoryFileName);
    }

    @Override
    public void delete(Long id) throws NoRecordException {
        categoryRepository.delete(id);
    }
}
