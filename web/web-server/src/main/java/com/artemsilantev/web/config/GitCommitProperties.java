package com.artemsilantev.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "git-info")
@Data
public class GitCommitProperties {
  private String commitId = "unknown";
}
