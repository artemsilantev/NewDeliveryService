package com.artemsilantev.loader.thread;

import java.util.Collection;
import java.util.concurrent.Executor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
public class StarterThread implements Runnable {

  private final Collection<Runnable> threads;
  private final Executor executor;

  public StarterThread(Collection<Runnable> threads) {
    this.threads = threads;
    var taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(threads.size());
    taskExecutor.setThreadNamePrefix("StarterThreadPool - ");
    taskExecutor.initialize();
    this.executor = taskExecutor;
  }

  @Override
  public void run() {
    log.info("Thread starter start");
    threads.forEach(this::startThread);
    log.info("Thread starter stop");
  }

  private void startThread(Runnable runnable) {
    executor.execute(runnable);
    try {
      Thread.sleep(100);
    } catch (InterruptedException interruptedException) {
      log.error("Thread starter stop working because of problem: {}",
          interruptedException.getMessage());
    }
  }

}
