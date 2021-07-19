package repositories;

import java.util.Collection;
import model.BaseEntity;

public interface AbstractRepository<E extends BaseEntity> {

  E create(E entity);

  E get(Long id);

  Collection<E> getAll();

  void save();

  void delete(Long id);
}
