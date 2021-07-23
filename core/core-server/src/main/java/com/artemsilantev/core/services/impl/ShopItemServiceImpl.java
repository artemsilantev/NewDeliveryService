package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.ShopItemDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.repositories.ShopItemRepository;
import com.artemsilantev.core.services.ShopItemService;

public class ShopItemServiceImpl extends AbstractServiceImpl<ShopItemDTO, ShopItem>
    implements ShopItemService {

  public ShopItemServiceImpl(Mapper<ShopItemDTO, ShopItem> mapper,
      ShopItemRepository shopItemRepository) {
    super(mapper, shopItemRepository);
  }

}
