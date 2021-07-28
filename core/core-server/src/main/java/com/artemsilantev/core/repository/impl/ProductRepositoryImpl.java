package com.artemsilantev.core.repository.impl;

import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.core.repository.ProductRepository;
import com.artemsilantev.core.storage.ProductDataStorage;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductRepositoryImpl extends BaseRepositoryImpl<Product>
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
  public Boolean isNameExists(String name) {
    return getAll().stream()
        .anyMatch(product -> product.getName().equals(name));
  }

  @Override
  public Boolean isNameExists(String name, Long id) {
    return getAll().stream()
        .anyMatch(product -> (product.getName().equals(name) && !product.getId().equals(id)));
  }

  @Override
  protected String getEntityName() {
    return "Product";
  }
}
