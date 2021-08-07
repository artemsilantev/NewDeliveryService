package com.artemsilantev.persistence.listener;

import com.artemsilantev.persistence.events.EntityDeleteEvent;
import com.artemsilantev.persistence.model.PersistenceBaseEntity;
import javax.persistence.PreRemove;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityAuditTrailsListener {

  @Autowired
  private ApplicationEventPublisher publisher;

  @PreRemove
  private void beforeRemove(PersistenceBaseEntity baseEntity) {
    var message = String.format("%s (id:%d) will be deleted", baseEntity.getClass().getSimpleName(),
        baseEntity.getId());
    publisher.publishEvent(new EntityDeleteEvent(this, message));
  }
}
