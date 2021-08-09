package com.artemsilantev.loader.sender.impl;

import com.artemsilantev.loader.sender.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class PutSender implements Sender {

  @Override
  public boolean send(Object o, String url) {
    var restTemplate = new RestTemplate();
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    var httpEntity = new HttpEntity<>(o, headers);
    try {
      restTemplate.exchange(url, HttpMethod.PUT, httpEntity,
          o.getClass());
    } catch (Exception errorException) {
      log.info(errorException.getMessage());
      return false;
    }
    return true;
  }
}
