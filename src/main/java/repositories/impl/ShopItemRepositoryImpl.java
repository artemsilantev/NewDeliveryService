package repositories.impl;

import model.ShopItem;
import repositories.ShopItemRepository;
import storages.ShopItemDataStorage;

public class ShopItemRepositoryImpl extends AbstractRepositoryImpl<ShopItem>
    implements ShopItemRepository {

    public ShopItemRepositoryImpl(ShopItemDataStorage shopItemDataStorage) {
        super(shopItemDataStorage);
    }
}
