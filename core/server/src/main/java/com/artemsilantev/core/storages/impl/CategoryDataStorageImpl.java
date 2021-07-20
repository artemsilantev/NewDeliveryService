package com.artemsilantev.core.storages.impl;

import com.artemsilantev.core.filemanagers.FileManager;
import com.artemsilantev.core.handlers.Handler;
import java.util.Collection;
import java.util.stream.Collectors;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.storages.CategoryDataStorage;
import com.artemsilantev.core.validators.Validator;

public class CategoryDataStorageImpl extends AbstractDataStorageImpl<Category>
    implements CategoryDataStorage {


  public CategoryDataStorageImpl(
      Handler<Mapper<Category, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<Category>> entityValidators) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
  }

  @Override
  protected Collection<Category> load() {
    Collection<Category> categories = super.load();
    return categories.stream()
        .map(category -> {
          if (category.getParent() != null) {
            category.setParent(get(category.getParent().getId(), categories));
          }
          return category;
        }).collect(Collectors.toList());
  }

  private Category get(Long id, Collection<Category> categories) {
    return categories.stream()
        .filter(category -> category.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

}
