package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.repository.ShopItemRepository;
import com.artemsilantev.persistence.mapper.ShopItemEntityMapper;
import com.artemsilantev.persistence.repository.JpaOrderRepository;
import com.artemsilantev.persistence.repository.JpaShopItemRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaShopItemRepositoryFacade implements ShopItemRepository {

  private final JpaShopItemRepository repository;
  private final JpaOrderRepository orderRepository;
  private final ShopItemEntityMapper mapper;

  @Override
  public ShopItem create(ShopItem entity) {
    return mapper.toTarget(repository.save(mapper.toSource(entity)));
  }

  @Override
  public ShopItem get(Long id) {
    return mapper.toTarget(repository.getById(id));
  }

  @Override
  public Collection<ShopItem> getAll() {
    return mapper.toTargetCollection(repository.findAll());
  }

  @Override
  public void delete(Long id) {
    orderRepository.findAll().forEach(order ->
        order.getItems().removeIf(shopItem ->
            shopItem.getId().equals(id)));
    repository.deleteById(id);
  }

  @Override
  public void update(ShopItem shopItem) {
    repository.save(mapper.toSource(shopItem));
  }
}
