package com.artemsilantev.core.mappers.impl.json;

import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.utils.GsonUtils;

public class ShopItemJsonMapper implements Mapper<ShopItem, String> {

  @Override
  public ShopItem toTarget(String source) {
    return (ShopItem) GsonUtils.deserialize(source, ShopItem.class);
  }

  @Override
  public String toSource(ShopItem target) {
    var shopOld = target.getShop();
    var productOld = target.getProduct();
    if (shopOld != null) {
      var shopNew = new Shop();
      shopNew.setId(shopOld.getId());
      target.setShop(shopNew);
    }
    if (productOld != null) {
      var productNew = new Product();
      productNew.setId(productOld.getId());
      target.setProduct(productNew);
    }
    String result = GsonUtils.serialize(target);
    target.setShop(shopOld);
    target.setProduct(productOld);
    return result;
  }
}
