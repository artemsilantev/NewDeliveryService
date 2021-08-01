package com.artemsilantev.persistence.facade;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.repository.UserRepository;
import com.artemsilantev.persistence.model.UserEntity;
import com.artemsilantev.persistence.repository.JpaOrderRepository;
import com.artemsilantev.persistence.repository.JpaUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class JpaUserRepositoryFacade extends JpaBaseRepositoryFacade<User, UserEntity>
    implements UserRepository {

  private final JpaOrderRepository orderRepository;

  public JpaUserRepositoryFacade(
      JpaRepository<UserEntity, Long> repository,
      JpaOrderRepository orderRepository,
      Mapper<User, UserEntity> mapper) {
    super(repository, mapper);
    this.orderRepository = orderRepository;
  }

  @Override
  public void delete(Long id) {
    orderRepository.deleteAll(orderRepository.findAllByUser_Id(id));
    repository.deleteById(id);
  }

  @Override
  public Boolean isEmailExists(String email) {
    return ((JpaUserRepository) repository).existsByEmail(email);
  }

  @Override
  public Boolean isEmailExists(String email, Long id) {
    return ((JpaUserRepository) repository).existsByEmailAndIdIsNot(email, id);
  }
}
