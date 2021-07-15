package repositories.impl;

import model.Shop;
import repositories.ShopRepository;
import storages.ShopDataStorage;

public class ShopRepositoryImpl extends AbstractRepositoryImpl<Shop>
    implements ShopRepository {

    public ShopRepositoryImpl(ShopDataStorage shopDataStorage) {
        super(shopDataStorage);
    }
}
