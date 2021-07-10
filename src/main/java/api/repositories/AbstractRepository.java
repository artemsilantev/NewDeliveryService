package api.repositories;

import exceptions.NoRecordException;
import model.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface AbstractRepository <E extends BaseEntity> {
    E create(E entity);
    Optional<E> get(Long id);
    Collection<E> getAll();
    void delete(Long id) throws NoRecordException;
}
