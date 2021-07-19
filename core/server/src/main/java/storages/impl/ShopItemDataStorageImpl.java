package storages.impl;

import configs.DataStorageConfiguration;
import java.util.Collection;
import java.util.stream.Collectors;
import model.Product;
import model.Shop;
import model.ShopItem;
import storages.ProductDataStorage;
import storages.ShopDataStorage;
import storages.ShopItemDataStorage;

public class ShopItemDataStorageImpl extends AbstractDataStorageImpl<ShopItem>
    implements ShopItemDataStorage {

  private final ShopDataStorage shopDataStorage;
  private final ProductDataStorage productDataStorage;

  public ShopItemDataStorageImpl(DataStorageConfiguration configuration,
      ShopDataStorage shopDataStorage,
      ProductDataStorage productDataStorage) {
    super(configuration);
    this.shopDataStorage = shopDataStorage;
    this.productDataStorage = productDataStorage;
  }

  @Override
  protected Collection<ShopItem> load() {
    Collection<ShopItem> shopItems = super.load();
    return shopItems.stream()
        .map(item -> {
          if (item.getShop() != null) {
            item.setShop(getShop(item.getShop().getId()));
          }
          if (item.getProduct() != null) {
            item.setProduct(getProduct(item.getProduct().getId()));
          }
          return item;
        }).collect(Collectors.toList());
  }

  private Product getProduct(Long id) {
    return productDataStorage.getEntities().stream()
        .filter(product -> product.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  private Shop getShop(Long id) {
    return shopDataStorage.getEntities().stream()
        .filter(shop -> shop.getId().equals(id))
        .findFirst()
        .orElse(null);
  }
}
