package com.artemsilantev.loader;

import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.loader.config.LoaderProperties;
import com.artemsilantev.loader.model.ConsumerConfig;
import com.artemsilantev.loader.sender.Sender;
import com.artemsilantev.loader.thread.ConsumerThread;
import com.artemsilantev.loader.model.LoadUnit;
import com.artemsilantev.loader.model.ProducerConfig;
import com.artemsilantev.loader.thread.ProducerThread;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class LoaderRunner implements ApplicationRunner {

  private final LoaderProperties properties;
  private final TaskScheduler taskScheduler;
  private final FileManager fileManager;
  private final Handler<Sender, HttpMethod> httpMethodHandler;
  private final Queue<LoadUnit> loaderQueue = new LinkedBlockingQueue<>();

  @Override
  public void run(ApplicationArguments args) {
    properties.getProducers().forEach(this::initializeProducers);
    properties.getConsumers().forEach(this::initializeConsumers);
  }

  private void initializeProducers(ProducerConfig config) {
    var url = properties.getServerUrl() + config.getMethodMapping();
    var producerThread = new ProducerThread(config.getPathToDirectory(), url,
        config.getHttpMethod(), fileManager, loaderQueue);
    taskScheduler.schedule(producerThread, new CronTrigger(config.getCronExpression()));
  }

  private void initializeConsumers(ConsumerConfig config) {
    var consumerThread = new ConsumerThread(loaderQueue, httpMethodHandler);
    taskScheduler.schedule(consumerThread, new CronTrigger(config.getCronExpression()));
  }
}
