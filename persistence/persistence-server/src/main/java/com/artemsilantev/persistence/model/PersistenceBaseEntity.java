package com.artemsilantev.persistence.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class PersistenceBaseEntity {

  @Id
  @Column(name = "ID_")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Override
  public boolean equals(Object entity) {
    if (this == entity) {
      return true;
    }
    if (entity == null || getClass() != entity.getClass()) {
      return false;
    }
    PersistenceBaseEntity that = (PersistenceBaseEntity) entity;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
