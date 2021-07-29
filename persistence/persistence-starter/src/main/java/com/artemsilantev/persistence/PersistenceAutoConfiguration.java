package com.artemsilantev.persistence;

import com.artemsilantev.core.CoreAutoConfiguration;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.persistence.facade.JpaCategoryRepositoryFacade;
import com.artemsilantev.persistence.mapper.CategoryEntityMapper;
import com.artemsilantev.persistence.repository.JpaCategoryRepository;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.artemsilantev.persistence"})
@EnableJpaRepositories(basePackages = {"com.artemsilantev.persistence.repository"})
@EntityScan(basePackages = {"com.artemsilantev.persistence.model"})
@AutoConfigureBefore({CoreAutoConfiguration.class})
public class PersistenceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public CategoryRepository getCategoryRepository(JpaCategoryRepository jpaCategoryRepository,
      CategoryEntityMapper mapper){
    return new JpaCategoryRepositoryFacade(jpaCategoryRepository, mapper);
  }
}
