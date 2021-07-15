package storages.impl;

import configs.DataStorageConfiguration;
import model.Shop;
import model.User;
import storages.ShopDataStorage;
import storages.UserDataStorage;

public class ShopDataStorageImpl extends AbstractDataStorageImpl<Shop>
        implements ShopDataStorage {

    public ShopDataStorageImpl(DataStorageConfiguration configuration) {
        super(configuration);
    }

    
}