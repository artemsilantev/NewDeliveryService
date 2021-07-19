package repositories.impl;

import model.Category;
import repositories.CategoryRepository;
import storages.CategoryDataStorage;

public class CategoryRepositoryImpl extends AbstractRepositoryImpl<Category>
    implements CategoryRepository {

  public CategoryRepositoryImpl(CategoryDataStorage categoryDataStorage) {
    super(categoryDataStorage);
  }

}
