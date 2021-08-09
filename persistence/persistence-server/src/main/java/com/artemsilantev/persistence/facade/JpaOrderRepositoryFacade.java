package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.exception.IllegalEntityException;
import com.artemsilantev.core.exception.NoRecordException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.repository.OrderRepository;
import com.artemsilantev.persistence.model.OrderEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

public class JpaOrderRepositoryFacade extends JpaBaseRepositoryFacade<Order, OrderEntity>
    implements OrderRepository {

  public JpaOrderRepositoryFacade(
      JpaRepository<OrderEntity, Long> repository,
      Mapper<Order, OrderEntity> mapper) {
    super(repository, mapper);
  }

  @Override
  protected NoRecordException createNoRecordException(Long id, String entityName) {
    return super.createNoRecordException(id, "Order");
  }

  @Override
  protected IllegalEntityException createIllegalEntityException(
      DataIntegrityViolationException exception, String entityName) {
    return super.createIllegalEntityException(exception, "Order");
  }
}
