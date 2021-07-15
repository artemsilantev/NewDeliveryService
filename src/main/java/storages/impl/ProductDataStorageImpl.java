package storages.impl;

import configs.DataStorageConfiguration;
import model.Category;
import model.Product;
import storages.CategoryDataStorage;
import storages.ProductDataStorage;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductDataStorageImpl extends AbstractDataStorageImpl<Product>
        implements ProductDataStorage {

    private final CategoryDataStorage categoryDataStorage;

    public ProductDataStorageImpl(DataStorageConfiguration configuration, CategoryDataStorage categoryDataStorage) {
        super(configuration);
        this.categoryDataStorage = categoryDataStorage;
    }

    @Override
    protected Collection<Product> load() {
        Collection<Product> products = super.load();
        return products.stream()
                .map(product -> {
                    product.setCategories(product.getCategories().stream().
                            map(category -> getCategory(category.getId()))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()));
                    return product;
                }).collect(Collectors.toList());
    }

    private Category getCategory(Long id) {
        return categoryDataStorage.getEntities()
                .stream().filter(category -> category.getId().equals(id))
                .findFirst().orElse(null);
    }

}
