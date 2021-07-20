package com.artemsilantev.core.services.impl;

import com.artemsilantev.core.dto.ShopDTO;
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
  public ShopDTO create(Shop entity) {
    for (Shop shop : abstractRepository.getAll()) {
      if (shop.getName().equals(entity.getName())) {
        entity.setId(shop.getId());
        return mapperDTO.toTarget(entity);
      }
    }
    entity.setId(abstractRepository.create(entity).getId());
    return mapperDTO.toTarget(entity);
  }
}
