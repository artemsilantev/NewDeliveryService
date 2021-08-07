package com.artemsilantev.persistence.events;

import org.springframework.context.ApplicationEvent;

public class EntityDeleteEvent extends ApplicationEvent {

  private final String message;

  public EntityDeleteEvent(Object source, String message) {
    super(source);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
