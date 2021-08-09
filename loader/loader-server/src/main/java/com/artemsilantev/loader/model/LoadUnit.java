package com.artemsilantev.loader.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

@AllArgsConstructor
@Getter
public class LoadUnit {

  private final Object objectToLoad;
  private final HttpMethod method;
  private final String url;
}
