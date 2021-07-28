package com.artemsilantev.core.mapper.impl.json;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.util.GsonUtils;
import java.util.Collection;
import java.util.stream.Collectors;

public class OrderJsonMapper implements Mapper<Order, String> {

  @Override
  public Order toTarget(String source) {
    return (Order) GsonUtils.deserialize(source, Order.class);
  }

  @Override
  public String toSource(Order target) {
    var itemsOld = target.getItems();
    var userOld = target.getUser();
    if (userOld != null) {
      var userNew = new User();
      userNew.setId(userOld.getId());
      target.setUser(userNew);
    }
    if (itemsOld != null && !itemsOld.isEmpty()) {
      target.setItems(itemsOld.stream()
          .map(itemOld -> {
                var itemNew = new ShopItem();
                itemNew.setId(itemOld.getId());
                return itemNew;
              }
          ).collect(Collectors.toList()));
    }
    String result = GsonUtils.serialize(target);
    target.setItems(itemsOld);
    target.setUser(userOld);
    return result;
  }

  @Override
  public Collection<Order> toTargetCollection(Collection<String> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<String> toSourceCollection(Collection<Order> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
