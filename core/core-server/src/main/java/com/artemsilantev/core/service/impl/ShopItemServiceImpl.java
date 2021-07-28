package com.artemsilantev.core.service.impl;

import com.artemsilantev.core.dto.ShopItemDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.repository.ShopItemRepository;
import com.artemsilantev.core.service.ShopItemService;

public class ShopItemServiceImpl extends BaseServiceImpl<ShopItemDto, ShopItem>
    implements ShopItemService {

  public ShopItemServiceImpl(Mapper<ShopItemDto, ShopItem> mapper,
      ShopItemRepository shopItemRepository) {
    super(mapper, shopItemRepository);
  }

}
