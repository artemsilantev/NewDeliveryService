package services.impl;

import dto.ShopItemDTO;
import mappers.Mapper;
import model.ShopItem;
import repositories.ShopItemRepository;
import services.ShopItemService;

public class ShopItemServiceImpl extends AbstractServiceImpl<ShopItemDTO, ShopItem>
    implements ShopItemService {

  public ShopItemServiceImpl(Mapper<ShopItemDTO, ShopItem> mapper,
      ShopItemRepository shopItemRepository) {
    super(mapper, shopItemRepository);
  }

}
