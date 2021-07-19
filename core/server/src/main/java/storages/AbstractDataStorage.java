package storages;

import java.util.Collection;
import model.BaseEntity;

public interface AbstractDataStorage<E extends BaseEntity> {

  Collection<E> getEntities();

  E create(E entity);

  void save();
}
