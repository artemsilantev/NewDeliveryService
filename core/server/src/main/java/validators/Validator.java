package validators;

import java.util.Collection;

public interface Validator<E> {

  Collection<String> validate(E entity);
}
