package controllers;

import api.services.CategoryService;
import dto.CategoryDTO;
import model.Category;

import java.util.Collection;

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryDTO add(Category category){
       return categoryService.create(category);
    }

    public Collection<CategoryDTO> getAll(){
        return categoryService.getAll();
    }

    public void save(){
        categoryService.save();
    }


}
