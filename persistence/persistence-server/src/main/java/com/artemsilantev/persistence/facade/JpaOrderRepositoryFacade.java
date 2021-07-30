package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.repository.OrderRepository;
import com.artemsilantev.persistence.mapper.OrderEntityMapper;
import com.artemsilantev.persistence.repository.JpaOrderRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaOrderRepositoryFacade implements OrderRepository {

  private final JpaOrderRepository repository;
  private final OrderEntityMapper mapper;

  @Override
  public Order create(Order entity) {
    return mapper.toTarget(repository.save(mapper.toSource(entity)));
  }

  @Override
  public Order get(Long id) {
    return mapper.toTarget(repository.getById(id));
  }

  @Override
  public Collection<Order> getAll() {
    return mapper.toTargetCollection(repository.findAll());
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Override
  public void update(Order order) {
    repository.save(mapper.toSource(order));
  }
}
