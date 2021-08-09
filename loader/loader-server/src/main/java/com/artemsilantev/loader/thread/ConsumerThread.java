package com.artemsilantev.loader.thread;

import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.loader.model.LoadUnit;
import com.artemsilantev.loader.sender.Sender;
import java.util.Queue;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

@AllArgsConstructor
@Slf4j
public class ConsumerThread implements Runnable {

  private final Queue<LoadUnit> loaderQueue;
  private final Handler<Sender, HttpMethod> handler;

  @Override
  public void run() {
    log.info("Consumer thread start");
    while (!loaderQueue.isEmpty()) {
      LoadUnit unit = loaderQueue.poll();
      sendRequest(unit);
      try {
        Thread.sleep(10000);
      } catch (InterruptedException interruptedException) {
        log.error("Consumer thread was interrupted: {}", interruptedException.getMessage());
        return;
      }
    }
    log.info("Consumer thread stop");
  }

  private void sendRequest(LoadUnit unit) {
    if (unit == null) {
      return;
    }
    handler.getHandler(unit.getMethod()).send(unit.getObjectToLoad(), unit.getUrl());
  }
}
