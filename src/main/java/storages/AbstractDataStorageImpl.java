package storages;

import api.utils.loaders.Loader;
import model.BaseEntity;

import java.util.Collection;

public abstract class AbstractDataStorageImpl<E extends BaseEntity>
        implements api.data.AbstractDataStorage<E> {

     private Long entityIdSequence;
     private Collection<E> entities;
     private Loader<E> loader;

     protected AbstractDataStorageImpl(Loader<E> loader){
      this.loader = loader;
     }

    @Override
    public synchronized Collection<E> getEntities() {
        if(entities==null)
            load();
        return entities;
    }

    @Override
    public E create(E entity) {
         entity.setId(++entityIdSequence);
         entities.add(entity);
         return entity;
    }


    private void load(){
         entities = loader.load();
         if(entities.isEmpty())
             entityIdSequence = 0L;
         else
             entityIdSequence = entities.stream().mapToLong(BaseEntity::getId).max().orElse(0L);
     }
 }
