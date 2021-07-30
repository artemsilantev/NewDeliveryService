package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.model.User;
import com.artemsilantev.core.repository.UserRepository;
import com.artemsilantev.persistence.mapper.UserEntityMapper;
import com.artemsilantev.persistence.repository.JpaOrderRepository;
import com.artemsilantev.persistence.repository.JpaUserRepository;
import java.util.Collection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaUserRepositoryFacade implements UserRepository {

  private final JpaUserRepository repository;
  private final JpaOrderRepository orderRepository;
  private final UserEntityMapper mapper;

  @Override
  public User create(User entity) {
    return mapper.toTarget(repository.save(mapper.toSource(entity)));
  }

  @Override
  public User get(Long id) {
    return mapper.toTarget(repository.getById(id));
  }

  @Override
  public Collection<User> getAll() {
    return mapper.toTargetCollection(repository.findAll());
  }

  @Override
  public void delete(Long id) {
    orderRepository.deleteAll(orderRepository.findAllByUser_Id(id));
    repository.deleteById(id);
  }

  @Override
  public void update(User user) {
    repository.save(mapper.toSource(user));
  }

  @Override
  public Boolean isEmailExists(String email) {
    return repository.existsByEmail(email);
  }

  @Override
  public Boolean isEmailExists(String email, Long id) {
    return repository.existsByEmailAndIdIsNot(email, id);
  }
}
