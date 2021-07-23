package com.artemsilantev.core.repositories.impl;

import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.repositories.ProductRepository;
import com.artemsilantev.core.storages.ProductDataStorage;

public class ProductRepositoryImpl extends AbstractRepositoryImpl<Product>
    implements ProductRepository {

  public ProductRepositoryImpl(ProductDataStorage productDataStorage) {
    super(productDataStorage);
  }

}
