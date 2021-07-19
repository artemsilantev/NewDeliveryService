package validators.entity;

import java.util.ArrayList;
import java.util.Collection;
import model.BaseEntity;
import validators.Validator;

public class EntityIdValidator<E extends BaseEntity> implements Validator<E> {

  @Override
  public Collection<String> validate(E entity) {
    var problems = new ArrayList<String>();
    problems.addAll(checkId(entity.getId()));
    return problems;
  }

  private Collection<String> checkId(Long id) {
    var problems = new ArrayList<String>();
    if (id == null) {
      problems.add("Id shouldn't be null - " + id);
    } else {
      if (id < 0L) {
        problems.add("Id shouldn't be negative - " + id);
      }
    }
    return problems;
  }
}
