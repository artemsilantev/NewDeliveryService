package com.artemsilantev.core.storage.impl;

import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.storage.CategoryDataStorage;
import com.artemsilantev.core.validator.Validator;
import java.util.Collection;
import java.util.stream.Collectors;

public class CategoryDataStorageImpl extends BaseDataStorageImpl<Category>
    implements CategoryDataStorage {


  public CategoryDataStorageImpl(
      Handler<Mapper<Category, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<Category>> entityValidators) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
  }


  @Override
  protected Collection<Category> fillReference(Collection<Category> entitiesToLoad) {
    return entitiesToLoad.stream()
        .map(category -> {
          if (category.getParent() != null) {
            category.setParent(findCategoryById(category.getParent().getId(), entitiesToLoad));
          }
          return category;
        }).collect(Collectors.toList());
  }

  private Category findCategoryById(Long id, Collection<Category> categories) {
    return categories.stream()
        .filter(category -> category.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

}
