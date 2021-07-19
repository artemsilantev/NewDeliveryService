package storages.impl;

import configs.DataStorageConfiguration;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import model.Order;
import model.ShopItem;
import model.User;
import storages.OrderDataStorage;
import storages.ShopItemDataStorage;
import storages.UserDataStorage;

public class OrderDataStorageImpl extends AbstractDataStorageImpl<Order>
    implements OrderDataStorage {

  private final ShopItemDataStorage shopItemDataStorage;
  private final UserDataStorage userDataStorage;

  public OrderDataStorageImpl(DataStorageConfiguration configuration,
      ShopItemDataStorage shopItemDataStorage, UserDataStorage userDataStorage) {
    super(configuration);
    this.shopItemDataStorage = shopItemDataStorage;
    this.userDataStorage = userDataStorage;
  }

  @Override
  protected Collection<Order> load() {
    Collection<Order> orders = super.load();
    return orders.stream()
        .map(order -> {
          if (order.getUser() != null) {
            order.setUser(getUser(order.getUser().getId()));
          }
          order.setItems(order.getItems().stream()
              .map(shopItem -> getShopItem(shopItem.getId()))
              .filter(Objects::nonNull)
              .collect(Collectors.toList()));
          return order;
        }).collect(Collectors.toList());
  }

  private ShopItem getShopItem(Long id) {
    return shopItemDataStorage.getEntities().stream()
        .filter(shopItem -> shopItem.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  private User getUser(Long id) {
    return userDataStorage.getEntities()
        .stream()
        .filter(user -> user.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

}
