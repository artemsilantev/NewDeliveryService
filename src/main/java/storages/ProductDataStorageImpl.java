package storages;

import api.data.CategoryDataStorage;
import api.data.ProductDataStorage;
import api.utils.loaders.Loader;
import model.Category;
import model.Product;

public class ProductDataStorageImpl extends AbstractDataStorageImpl<Product>
    implements ProductDataStorage {

    public ProductDataStorageImpl(Loader<Product> loader) {
        super(loader);
    }
}
