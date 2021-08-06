package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.repository.ShopRepository;
import com.artemsilantev.persistence.model.ShopEntity;
import com.artemsilantev.persistence.repository.JpaShopRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class JpaShopRepositoryFacade extends JpaBaseRepositoryFacade<Shop, ShopEntity>
    implements ShopRepository {

  public JpaShopRepositoryFacade(
      JpaRepository<ShopEntity, Long> repository,
      Mapper<Shop, ShopEntity> mapper) {
    super(repository, mapper);
  }

  @Override
  @Transactional(readOnly = true)
  public Boolean isNameExists(String name) {
    return ((JpaShopRepository) repository).existsByName(name);
  }

  @Override
  @Transactional(readOnly = true)
  public Boolean isNameExists(String name, Long id) {
    return ((JpaShopRepository) repository).existsByNameAndIdIsNot(name, id);
  }

  @Override
  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return super.createNoRecordException(id, "Shop");
  }
}
