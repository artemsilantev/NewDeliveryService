package storages;

import model.BaseEntity;

import java.util.Collection;

public interface AbstractDataStorage<E extends BaseEntity> {
    Collection<E> getEntities();
    E create(E entity);
    void save();
}
