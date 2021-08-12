package com.artemsilantev.loader;

import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.loader.config.LoaderProperties;
import com.artemsilantev.loader.model.LoadConfig;
import com.artemsilantev.loader.model.LoadUnit;
import com.artemsilantev.loader.sender.Sender;
import com.artemsilantev.loader.thread.ConsumerThread;
import com.artemsilantev.loader.thread.ProducerThread;
import com.artemsilantev.loader.thread.StarterThread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@RequiredArgsConstructor
@Slf4j
public class LoaderRunner {

  private final LoaderProperties properties;
  private final TaskScheduler taskScheduler;
  private final FileManager fileManager;
  private final Handler<Sender, HttpMethod> httpMethodHandler;
  private final Queue<LoadUnit> loaderQueue = new LinkedBlockingQueue<>();

  @PostConstruct
  public void run() {
    var threads = new ArrayList<Runnable>();
    threads.addAll(getProducersThread());
    threads.addAll(getConsumersThread());
    taskScheduler.schedule(new StarterThread(threads), new CronTrigger(
        properties.getCronExpression()));
  }

  private Collection<ProducerThread> getProducersThread() {
    var producerThreads = new ArrayList<ProducerThread>();
    properties.getLoadConfig().forEach(config -> {
      producerThreads.add(createProducerThread(HttpMethod.POST, config));
      producerThreads.add(createProducerThread(HttpMethod.PUT, config));
    });
    return producerThreads;
  }

  private ProducerThread createProducerThread(HttpMethod httpMethod,
      LoadConfig loadConfig) {
    var pathFinal = properties.getDirectoryPath() +
        loadConfig.getDirectoryName() +
        httpMethod.toString().toLowerCase(Locale.ROOT);
    var urlFinal = properties.getServerUrl() + loadConfig.getControllerUrl();
    return new ProducerThread(pathFinal, urlFinal, httpMethod, fileManager, loaderQueue);
  }

  private Collection<ConsumerThread> getConsumersThread() {
    var consumerThreads = new ArrayList<ConsumerThread>();
    for (var i = 0; i < properties.getThreads(); i++) {
      consumerThreads.add(new ConsumerThread(loaderQueue, httpMethodHandler));
    }
    return consumerThreads;
  }
}
