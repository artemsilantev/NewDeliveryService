package com.artemsilantev.core.storages.impl;

import com.artemsilantev.core.filemanagers.FileManager;
import com.artemsilantev.core.handlers.Handler;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.storages.CategoryDataStorage;
import com.artemsilantev.core.storages.ProductDataStorage;
import com.artemsilantev.core.validators.Validator;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductDataStorageImpl extends AbstractDataStorageImpl<Product>
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
