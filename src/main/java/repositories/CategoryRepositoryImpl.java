package repositories;

import api.data.CategoryDataStorage;
import api.repositories.CategoryRepository;
import model.Category;

public class CategoryRepositoryImpl extends AbstractRepositoryImpl<Category>
    implements CategoryRepository {

    public CategoryRepositoryImpl(CategoryDataStorage categoryDataStorage){
        super(categoryDataStorage);
    }

}
