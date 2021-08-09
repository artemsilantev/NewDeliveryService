package com.artemsilantev.loader.handler;


import com.artemsilantev.core.exception.NoHandlerException;
import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.loader.sender.Sender;
import com.artemsilantev.loader.sender.impl.PostSender;
import com.artemsilantev.loader.sender.impl.PutSender;
import org.springframework.http.HttpMethod;

public class RequestSenderHandler implements Handler<Sender, HttpMethod> {

  @Override
  public Sender getHandler(HttpMethod source) {
    switch (source) {
      case POST: {
        return new PostSender();
      }
      case PUT: {
        return new PutSender();
      }
      default: {
        throw new NoHandlerException(String.format("Couldn't find sender for %s method", source));
      }
    }
  }
}
