package api.utils.loaders;

import model.BaseEntity;

import java.util.Collection;

public interface Loader<E extends BaseEntity> {
    Collection<E> load();
}
