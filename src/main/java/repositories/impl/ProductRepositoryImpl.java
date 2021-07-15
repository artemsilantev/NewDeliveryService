package repositories.impl;

import repositories.ProductRepository;
import storages.ProductDataStorage;
import model.Product;

public class ProductRepositoryImpl extends AbstractRepositoryImpl<Product>
    implements ProductRepository {

    public ProductRepositoryImpl(ProductDataStorage productDataStorage){
        super(productDataStorage);
    }

}
