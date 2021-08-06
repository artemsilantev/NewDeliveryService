package com.artemsilantev.persistence;

import com.artemsilantev.core.CoreAutoConfiguration;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.core.repository.OrderRepository;
import com.artemsilantev.core.repository.ProductRepository;
import com.artemsilantev.core.repository.ShopItemRepository;
import com.artemsilantev.core.repository.ShopRepository;
import com.artemsilantev.core.repository.UserRepository;
import com.artemsilantev.persistence.facade.JpaCategoryRepositoryFacade;
import com.artemsilantev.persistence.facade.JpaOrderRepositoryFacade;
import com.artemsilantev.persistence.facade.JpaProductRepositoryFacade;
import com.artemsilantev.persistence.facade.JpaShopItemRepositoryFacade;
import com.artemsilantev.persistence.facade.JpaShopRepositoryFacade;
import com.artemsilantev.persistence.facade.JpaUserRepositoryFacade;
import com.artemsilantev.persistence.mapper.CategoryEntityMapper;
import com.artemsilantev.persistence.mapper.OrderEntityMapper;
import com.artemsilantev.persistence.mapper.ProductEntityMapper;
import com.artemsilantev.persistence.mapper.ShopEntityMapper;
import com.artemsilantev.persistence.mapper.ShopItemEntityMapper;
import com.artemsilantev.persistence.mapper.UserEntityMapper;
import com.artemsilantev.persistence.repository.JpaCategoryRepository;
import com.artemsilantev.persistence.repository.JpaOrderRepository;
import com.artemsilantev.persistence.repository.JpaProductRepository;
import com.artemsilantev.persistence.repository.JpaShopItemRepository;
import com.artemsilantev.persistence.repository.JpaShopRepository;
import com.artemsilantev.persistence.repository.JpaUserRepository;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.artemsilantev.persistence"})
@EnableJpaRepositories(basePackages = {"com.artemsilantev.persistence.repository"})
@EntityScan(basePackages = {"com.artemsilantev.persistence.model"})
@EnableTransactionManagement
@AutoConfigureBefore({CoreAutoConfiguration.class})
public class PersistenceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public CategoryRepository getCategoryRepository(JpaCategoryRepository categoryRepository,
      CategoryEntityMapper mapper) {
    return new JpaCategoryRepositoryFacade(categoryRepository, mapper);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopItemRepository getShopItemRepository(JpaShopItemRepository shopItemRepository,
      ShopItemEntityMapper mapper) {
    return new JpaShopItemRepositoryFacade(shopItemRepository, mapper);
  }

  @Bean
  @ConditionalOnMissingBean
  public ProductRepository getProductRepository(JpaProductRepository productRepository,
      ProductEntityMapper mapper) {
    return new JpaProductRepositoryFacade(productRepository, mapper);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopRepository getShopRepository(JpaShopRepository shopRepository,
      ShopEntityMapper mapper) {
    return new JpaShopRepositoryFacade(shopRepository, mapper);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserRepository getUserRepository(JpaUserRepository userRepository,
      UserEntityMapper mapper) {
    return new JpaUserRepositoryFacade(userRepository, mapper);
  }

  @Bean
  @ConditionalOnMissingBean
  public OrderRepository getOrderRepository(JpaOrderRepository orderRepository,
      OrderEntityMapper mapper) {
    return new JpaOrderRepositoryFacade(orderRepository, mapper);
  }
}
