package storages.impl;

import configs.DataStorageConfiguration;
import model.Category;
import storages.CategoryDataStorage;

import java.util.Collection;
import java.util.stream.Collectors;

public class CategoryDataStorageImpl extends AbstractDataStorageImpl<Category>
        implements CategoryDataStorage {

    public CategoryDataStorageImpl(DataStorageConfiguration configuration) {
        super(configuration);
    }

    @Override
    protected Collection<Category> load() {
        Collection<Category> categories = super.load();
        return categories.stream().map(category -> {
            if (category.getParent() != null) {
                category.setParent(get(category.getParent().getId(), categories));
            }
            return category;
        }).collect(Collectors.toList());
    }

    private Category get(Long id, Collection<Category> categories) {
        return categories.stream().filter(category -> category.getId().equals(id))
                .findFirst().orElse(null);
    }

}
