package repositories;

import api.data.CategoryDataStorage;
import api.data.ProductDataStorage;
import api.repositories.CategoryRepository;
import api.repositories.ProductRepository;
import model.Category;
import model.Product;

public class ProductRepositoryImpl extends AbstractRepositoryImpl<Product>
    implements ProductRepository {

    public ProductRepositoryImpl(ProductDataStorage productDataStorage){
        super(productDataStorage);
    }

}
