package repositories.impl;

import repositories.CategoryRepository;
import storages.CategoryDataStorage;
import model.Category;

public class CategoryRepositoryImpl extends AbstractRepositoryImpl<Category>
    implements CategoryRepository {

    public CategoryRepositoryImpl(CategoryDataStorage categoryDataStorage){
        super(categoryDataStorage);
    }

}
