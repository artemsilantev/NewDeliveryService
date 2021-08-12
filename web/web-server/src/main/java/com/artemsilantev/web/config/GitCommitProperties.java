package com.artemsilantev.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "delivery-service.git-commit-id")
@Data
public class GitCommitProperties {
  private String idAbbrev = "unknown";
  private String message = "unknown";
  private String author = "unknown";
  private String branch = "unknown";
}
