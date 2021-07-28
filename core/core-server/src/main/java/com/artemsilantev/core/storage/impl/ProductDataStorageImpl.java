package com.artemsilantev.core.storage.impl;

import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.storage.CategoryDataStorage;
import com.artemsilantev.core.storage.ProductDataStorage;
import com.artemsilantev.core.validator.Validator;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductDataStorageImpl extends BaseDataStorageImpl<Product>
    implements ProductDataStorage {

  private final CategoryDataStorage categoryDataStorage;

  public ProductDataStorageImpl(
      Handler<Mapper<Product, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<Product>> entityValidators,
      CategoryDataStorage categoryDataStorage) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
    this.categoryDataStorage = categoryDataStorage;
  }


  @Override
  protected Collection<Product> fillReference(Collection<Product> entitiesToLoad) {
    return entitiesToLoad.stream()
        .map(product -> {
          product.setCategories(product.getCategories().stream()
              .map(category -> findCategoryById(category.getId()))
              .filter(Objects::nonNull)
              .collect(Collectors.toList()));
          return product;
        }).collect(Collectors.toList());
  }


  private Category findCategoryById(Long id) {
    return categoryDataStorage.getEntities().stream()
        .filter(category -> category.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

}
