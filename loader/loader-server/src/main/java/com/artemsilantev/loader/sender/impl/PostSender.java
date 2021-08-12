package com.artemsilantev.loader.sender.impl;

import com.artemsilantev.loader.sender.Sender;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class PostSender implements Sender {

  @Override
  public boolean send(Object o, String url) {
    var restTemplate = new RestTemplate();
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    var httpEntity = new HttpEntity<>(o, headers);
    try {
      restTemplate.postForEntity(url, httpEntity,
          o.getClass());
    } catch (Exception errorException) {
      return false;
    }
    return true;
  }
}
