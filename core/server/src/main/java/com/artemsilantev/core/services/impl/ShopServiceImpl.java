package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.ShopDTO;
import com.artemsilantev.core.exceptions.IllegalEntityException;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.repositories.ShopRepository;
import com.artemsilantev.core.services.ShopService;

public class ShopServiceImpl extends AbstractServiceImpl<ShopDTO, Shop>
    implements ShopService {

  public ShopServiceImpl(Mapper<ShopDTO, Shop> mapper, ShopRepository shopRepository) {
    super(mapper, shopRepository);
  }

  @Override
  public ShopDTO create(ShopDTO shopDTO) {
    for (Shop shop : abstractRepository.getAll()) {
      if (shop.getName().equals(shopDTO.getName())) {
        throw new IllegalEntityException(
            String.format("Shop with this name already exists: %s", shopDTO.getName()));
      }
    }
    return super.create(shopDTO);
  }

  @Override
  public void update(ShopDTO shopDTO) {
    for (Shop shop : abstractRepository.getAll()) {
      if (shop.getName().equals(shopDTO.getName())
          && !shop.getId().equals(shopDTO.getId())) {
        throw new IllegalEntityException(
            String.format("Shop with this name already exists: %s", shopDTO.getName()));
      }
      super.update(shopDTO);
    }


  }
}
