package storages;

import api.data.CategoryDataStorage;
import api.utils.loaders.Loader;
import model.Category;

public class CategoryDataStorageImpl extends AbstractDataStorageImpl<Category>
    implements CategoryDataStorage {

    public CategoryDataStorageImpl(Loader<Category> loader) {
        super(loader);
    }
}
