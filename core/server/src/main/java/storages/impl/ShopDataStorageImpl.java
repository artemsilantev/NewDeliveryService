package storages.impl;

import configs.DataStorageConfiguration;
import model.Shop;
import storages.ShopDataStorage;

public class ShopDataStorageImpl extends AbstractDataStorageImpl<Shop>
    implements ShopDataStorage {

  public ShopDataStorageImpl(DataStorageConfiguration configuration) {
    super(configuration);
  }


}