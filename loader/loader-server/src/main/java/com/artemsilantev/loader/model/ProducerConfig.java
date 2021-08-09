package com.artemsilantev.loader.model;

import lombok.Data;
import org.springframework.http.HttpMethod;


@Data
public class ProducerConfig {

  private String pathToDirectory;
  private HttpMethod httpMethod;
  private String methodMapping;
  private String cronExpression;
}
