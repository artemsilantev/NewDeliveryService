package services.impl;

import dto.ShopDTO;
import mappers.Mapper;
import model.Shop;
import repositories.ShopRepository;
import services.ShopService;

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
