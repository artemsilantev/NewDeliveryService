package com.artemsilantev.loader.config;

import com.artemsilantev.loader.model.LoadConfig;
import java.util.Collection;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "delivery-service.loader")
@Data
public class LoaderProperties {

  private String serverUrl = "http://localhost:8080";
  private String directoryPath = "Hot-load\\'";
  private String cronExpression = "0 0 12 * * ?";
  private Integer threads = 3;
  private Boolean enabled = true;
  private Collection<LoadConfig> loadConfig;

}
