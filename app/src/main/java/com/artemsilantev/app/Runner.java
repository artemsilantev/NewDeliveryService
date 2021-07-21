package com.artemsilantev.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Runner implements ApplicationRunner {

  @Autowired
  private Test test;

  public static void main(String[] args) {
    SpringApplication.run(Runner.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    test.run();
  }
}
