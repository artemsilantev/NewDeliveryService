package validators.entity;

import validators.ObjectValidator;
import model.BaseEntity;


import java.util.ArrayList;
import java.util.Collection;

public class EntityIdValidator implements ObjectValidator {
    @Override
    public Collection<String> validate(Object obj) {
        var problems = new ArrayList<String>();
        problems.addAll(checkId(((BaseEntity)obj).getId()));
        return problems;
    }

    private Collection<String> checkId(Long id) {
        var problems = new ArrayList<String>();
        if (id == null) {
            problems.add("Id shouldn't be null");
        } else {
            if (id < 0L) {
                problems.add("Id shouldn't be negative");
            }
        }
        return problems;
    }
}
