package com.artemsilantev.core.service.impl;

import com.artemsilantev.core.dto.ShopDto;
import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.repository.ShopRepository;
import com.artemsilantev.core.service.ShopService;

public class ShopServiceImpl extends BaseServiceImpl<ShopDto, Shop>
    implements ShopService {

  public ShopServiceImpl(Mapper<ShopDto, Shop> mapper, ShopRepository shopRepository) {
    super(mapper, shopRepository);
  }

  @Override
  public ShopDto create(ShopDto shopDto) {
    if (((ShopRepository) baseRepository).isNameExists(shopDto.getName())) {
      throw new IllegalEntityException(
          String.format("Shop with this name already exists: %s", shopDto.getName()));
    }
    return super.create(shopDto);
  }

  @Override
  public ShopDto update(ShopDto shopDto) {
    if (((ShopRepository) baseRepository).isNameExists(shopDto.getName(), shopDto.getId())) {
      throw new IllegalEntityException(
          String.format("Shop with this name already exists: %s", shopDto.getName()));
    }
    return super.update(shopDto);
  }
}
