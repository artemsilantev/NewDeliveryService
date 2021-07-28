package com.artemsilantev.core.repository;

import com.artemsilantev.core.model.Product;

public interface ProductRepository extends BaseRepository<Product> {

  Boolean isNameExists(String name);

  Boolean isNameExists(String name, Long id);
}
