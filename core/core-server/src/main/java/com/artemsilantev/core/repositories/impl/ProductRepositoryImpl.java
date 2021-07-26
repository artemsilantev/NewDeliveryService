package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.repositories.CategoryRepository;
import com.artemsilantev.core.repositories.ProductRepository;
import com.artemsilantev.core.storages.ProductDataStorage;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductRepositoryImpl extends AbstractRepositoryImpl<Product>
    implements ProductRepository {

  private final CategoryRepository categoryRepository;

  public ProductRepositoryImpl(ProductDataStorage productDataStorage,
      CategoryRepository categoryRepository) {
    super(productDataStorage);
    this.categoryRepository = categoryRepository;
  }

  @Override
  protected void fillReference(Product entity) {
    if(entity.getCategories()==null) {
      entity.setCategories(new ArrayList<>());
    }
    entity.setCategories(entity.getCategories().stream()
        .map(category -> categoryRepository.get(category.getId()))
        .collect(Collectors.toList()));
  }

  @Override
  protected String getEntityName() {
    return "Product";
  }
}
