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
  public ShopDto create(ShopDto shopDTO) {
    if (((ShopRepository) baseRepository).isNameExists(shopDTO.getName())) {
      throw new IllegalEntityException(
          String.format("Shop with this name already exists: %s", shopDTO.getName()));
    }
    return super.create(shopDTO);
  }

  @Override
  public void update(ShopDto shopDTO) {
    if (((ShopRepository) baseRepository).isNameExists(shopDTO.getName(), shopDTO.getId())) {
      throw new IllegalEntityException(
          String.format("Shop with this name already exists: %s", shopDTO.getName()));
    }
    super.update(shopDTO);
  }
}
