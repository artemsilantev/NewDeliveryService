package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.repository.ShopRepository;
import com.artemsilantev.persistence.mapper.ShopEntityMapper;
import com.artemsilantev.persistence.repository.JpaOrderRepository;
import com.artemsilantev.persistence.repository.JpaShopItemRepository;
import com.artemsilantev.persistence.repository.JpaShopRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaShopRepositoryFacade implements ShopRepository {

  private final JpaShopRepository repository;
  private final JpaShopItemRepository shopItemRepository;
  private final JpaOrderRepository orderRepository;
  private final ShopEntityMapper mapper;

  @Override
  public Shop create(Shop entity) {
    return mapper.toTarget(mapper.toSource(entity));
  }

  @Override
  public Shop get(Long id) {
    return mapper.toTarget(repository.getById(id));
  }

  @Override
  public Collection<Shop> getAll() {
    return mapper.toTargetCollection(repository.findAll());
  }

  @Override
  public void delete(Long id) {
    var shopItems = shopItemRepository.findAllByShop_Id(id);
    orderRepository.findAll().forEach(orderEntity -> orderEntity.getItems().removeAll(shopItems));
    shopItemRepository.deleteAll(shopItems);
    repository.deleteById(id);
  }

  @Override
  public void update(Shop shop) {
    repository.save(mapper.toSource(shop));
  }

  @Override
  public Boolean isNameExists(String name) {
    return repository.existsByName(name);
  }

  @Override
  public Boolean isNameExists(String name, Long id) {
    return repository.existsByNameAndIdIsNot(name, id);
  }
}
