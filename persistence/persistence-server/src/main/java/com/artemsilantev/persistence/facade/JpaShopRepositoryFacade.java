package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.repository.ShopRepository;
import com.artemsilantev.persistence.model.ShopEntity;
import com.artemsilantev.persistence.repository.JpaOrderRepository;
import com.artemsilantev.persistence.repository.JpaShopItemRepository;
import com.artemsilantev.persistence.repository.JpaShopRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class JpaShopRepositoryFacade extends JpaBaseRepositoryFacade<Shop, ShopEntity>
    implements ShopRepository {

  private final JpaShopItemRepository shopItemRepository;
  private final JpaOrderRepository orderRepository;

  public JpaShopRepositoryFacade(
      JpaRepository<ShopEntity, Long> repository,
      JpaShopItemRepository shopItemRepository,
      JpaOrderRepository orderRepository,
      Mapper<Shop, ShopEntity> mapper) {
    super(repository, mapper);
    this.shopItemRepository = shopItemRepository;
    this.orderRepository = orderRepository;
  }

  @Override
  public Boolean isNameExists(String name) {
    return ((JpaShopRepository) repository).existsByName(name);
  }

  @Override
  public Boolean isNameExists(String name, Long id) {
    return ((JpaShopRepository) repository).existsByNameAndIdIsNot(name, id);
  }

  @Override
  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return super.createNoRecordException(id, "Shop");
  }
}
