package com.artemsilantev.loader;

import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.loader.handler.RequestSenderHandler;
import com.artemsilantev.loader.sender.Sender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = {"com.artemsilantev.loader"})
public class LoaderAutoConfiguration {

  @Bean
  public TaskScheduler getAsyncTaskScheduler() {
    var taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    taskScheduler.setThreadNamePrefix("AsyncTaskScheduler - ");
    return taskScheduler;
  }

  @Bean
  public Handler<Sender, HttpMethod> getHttpMethodHandler() {
    return new RequestSenderHandler();
  }
}
