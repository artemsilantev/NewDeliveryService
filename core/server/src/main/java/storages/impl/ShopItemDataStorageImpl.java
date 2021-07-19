package storages.impl;

import filemanagers.FileManager;
import handlers.Handler;
import java.util.Collection;
import java.util.stream.Collectors;
import mappers.Mapper;
import model.Product;
import model.Shop;
import model.ShopItem;
import storages.ProductDataStorage;
import storages.ShopDataStorage;
import storages.ShopItemDataStorage;
import validators.Validator;

public class ShopItemDataStorageImpl extends AbstractDataStorageImpl<ShopItem>
    implements ShopItemDataStorage {

  private final ShopDataStorage shopDataStorage;
  private final ProductDataStorage productDataStorage;

  public ShopItemDataStorageImpl(
      Handler<Mapper<ShopItem, String>, String> mapperHandler,
      FileManager fileManager, String pathToFile,
      Collection<Validator<String>> textValidators,
      Collection<Validator<ShopItem>> entityValidators, ShopDataStorage shopDataStorage,
      ProductDataStorage productDataStorage) {
    super(mapperHandler, fileManager, pathToFile, textValidators, entityValidators);
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
