package com.artemsilantev.persistence.model;

import com.artemsilantev.persistence.listener.EntityAuditTrailsListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Audited
@EntityListeners(EntityAuditTrailsListener.class)
@Table(name = "CATEGORY_")
public class CategoryEntity extends PersistenceBaseEntity {

  @Column(name = "NAME_", nullable = false, unique = true)
  private String name;

  @Column(name = "DESCRIPTION_")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT_CATEGORY_ID_", referencedColumnName = "ID_")
  private CategoryEntity parent;

  @Column(name = "PARENT_CATEGORY_ID_", updatable = false, insertable = false)
  private Long parentId;

}
