package repositories.impl;

import model.Product;
import repositories.ProductRepository;
import storages.ProductDataStorage;

public class ProductRepositoryImpl extends AbstractRepositoryImpl<Product>
    implements ProductRepository {

  public ProductRepositoryImpl(ProductDataStorage productDataStorage) {
    super(productDataStorage);
  }

}
