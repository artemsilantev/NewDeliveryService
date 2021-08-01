package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.repository.ProductRepository;
import com.artemsilantev.persistence.model.ProductEntity;
import com.artemsilantev.persistence.repository.JpaOrderRepository;
import com.artemsilantev.persistence.repository.JpaProductRepository;
import com.artemsilantev.persistence.repository.JpaShopItemRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class JpaProductRepositoryFacade extends JpaBaseRepositoryFacade<Product, ProductEntity>
    implements ProductRepository {

  private final JpaShopItemRepository shopItemRepository;
  private final JpaOrderRepository orderRepository;

  public JpaProductRepositoryFacade(
      JpaRepository<ProductEntity, Long> repository,
      JpaShopItemRepository shopItemRepository,
      JpaOrderRepository orderRepository,
      Mapper<Product, ProductEntity> mapper) {
    super(repository, mapper);
    this.shopItemRepository = shopItemRepository;
    this.orderRepository = orderRepository;
  }

  @Override
  public void delete(Long id) {
    var shopItems = shopItemRepository.findAllByProduct_Id(id);
    orderRepository.findAll().forEach(order -> order.getItems().removeAll(shopItems));
    shopItemRepository.deleteAll(shopItems);
    repository.deleteById(id);
  }

  @Override
  public Boolean isNameExists(String name) {
    return ((JpaProductRepository) repository).existsByName(name);
  }

  @Override
  public Boolean isNameExists(String name, Long id) {
    return ((JpaProductRepository) repository).existsByNameAndIdIsNot(name, id);
  }

  @Override
  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return super.createNoRecordException(id, "Product");
  }
}
