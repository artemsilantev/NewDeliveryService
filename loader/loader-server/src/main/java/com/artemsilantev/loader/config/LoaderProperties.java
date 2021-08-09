package com.artemsilantev.loader.config;

import com.artemsilantev.loader.model.ConsumerConfig;
import com.artemsilantev.loader.model.ProducerConfig;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "loader-info")
@Data
public class LoaderProperties {

  private String serverUrl = "http://localhost:8080";
  private Collection<ProducerConfig> producers = new ArrayList<>();
  private Collection<ConsumerConfig> consumers = new ArrayList<>();
}
