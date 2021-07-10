package repositories;

import api.data.AbstractDataStorage;
import api.repositories.AbstractRepository;
import exceptions.NoRecordException;
import model.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public abstract class AbstractRepositoryImpl <E extends BaseEntity>
        implements AbstractRepository<E> {

    protected final AbstractDataStorage<E> dataStorage;

    protected AbstractRepositoryImpl(AbstractDataStorage abstractDataStorage){
        dataStorage = abstractDataStorage;
    }

    @Override
    public E create(E entity) {
        return dataStorage.create(entity);
    }

    @Override
    public Optional<E> get(Long id) {
        return dataStorage.getEntities()
                .stream()
                .filter(entity->entity.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<E> getAll() {
        return dataStorage.getEntities();
    }

    @Override
    public void delete(Long id) throws NoRecordException {
        E entityToDelete = dataStorage.getEntities()
                .stream()
                .filter(entity->entity.getId()==id)
                .findFirst()
                .orElseThrow(()->new NoRecordException("entity", id));
        dataStorage.getEntities().remove(entityToDelete);
    }
}
