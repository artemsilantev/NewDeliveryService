package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.repository.ShopItemRepository;
import com.artemsilantev.persistence.model.ShopItemEntity;
import com.artemsilantev.persistence.repository.JpaOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class JpaShopItemRepositoryFacade extends JpaBaseRepositoryFacade<ShopItem, ShopItemEntity>
    implements ShopItemRepository {

  private final JpaOrderRepository orderRepository;

  public JpaShopItemRepositoryFacade(
      JpaRepository<ShopItemEntity, Long> repository,
      JpaOrderRepository orderRepository,
      Mapper<ShopItem, ShopItemEntity> mapper) {
    super(repository, mapper);
    this.orderRepository = orderRepository;
  }

  @Override
  public void delete(Long id) {
    orderRepository.findAll().forEach(order ->
        order.getItems().removeIf(shopItem ->
            shopItem.getId().equals(id)));
    repository.deleteById(id);
  }

  @Override
  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return super.createNoRecordException(id, "Shop Item");
  }
}
