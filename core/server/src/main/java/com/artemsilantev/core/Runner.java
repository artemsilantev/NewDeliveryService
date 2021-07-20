package com.artemsilantev.core;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Runner {

  public static void main(String[] args) {

  }

  private static void displayInfo(Object obj) {

    log.debug("{}", obj.toString());
  }

}
