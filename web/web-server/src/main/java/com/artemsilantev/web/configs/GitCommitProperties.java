package com.artemsilantev.web.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "git")
@Data
public class GitCommitProperties {
  private String id = "unknown";
}
