package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.repository.ShopItemRepository;
import com.artemsilantev.persistence.model.ShopItemEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

public class JpaShopItemRepositoryFacade extends JpaBaseRepositoryFacade<ShopItem, ShopItemEntity>
    implements ShopItemRepository {

  public JpaShopItemRepositoryFacade(
      JpaRepository<ShopItemEntity, Long> repository,
      Mapper<ShopItem, ShopItemEntity> mapper) {
    super(repository, mapper);
  }

  @Override
  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return super.createNoRecordException(id, "Shop Item");
  }

  @Override
  protected IllegalEntityException createIllegalEntityException(
      DataIntegrityViolationException exception, String entityName) {
    return super.createIllegalEntityException(exception, "Shop Item");
  }
}
