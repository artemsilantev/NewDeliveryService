package com.artemsilantev.persistence.listener;

import com.artemsilantev.persistence.events.EntityDeleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EntityDeleteEventListener implements ApplicationListener<EntityDeleteEvent> {

  @Override
  public void onApplicationEvent(EntityDeleteEvent entityDeleteEvent) {
    log.info("{}", entityDeleteEvent.getMessage());
  }
}
