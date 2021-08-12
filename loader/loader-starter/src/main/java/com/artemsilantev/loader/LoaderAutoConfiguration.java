package com.artemsilantev.loader;

import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.filemanager.TextFileManger;
import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.loader.config.LoaderProperties;
import com.artemsilantev.loader.handler.RequestSenderHandler;
import com.artemsilantev.loader.sender.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
@EnableScheduling
@ConditionalOnProperty(
    prefix = "delivery-service",
    value = "loader.enabled"
)
@ComponentScan(basePackages = {"com.artemsilantev.loader"})
@Slf4j
public class LoaderAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public Handler<Sender, HttpMethod> getHttpMethodHandler() {
    return new RequestSenderHandler();
  }

  @Bean
  @ConditionalOnMissingBean
  public FileManager getTextFileManager() {
    return new TextFileManger();
  }

  @Bean
  @ConditionalOnMissingBean
  public LoaderRunner getLoaderRunner(LoaderProperties properties, FileManager fileManager,
      Handler<Sender, HttpMethod> handler) {
    log.warn("LOAD RUNNER ENABLED");
    var taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(5);
    taskScheduler.setThreadNamePrefix("LoaderRunnerTaskScheduler - ");
    taskScheduler.initialize();
    return new LoaderRunner(properties, taskScheduler, fileManager, handler);
  }
}
