package utils.validators.object;

import api.utils.validators.ObjectValidator;
import exceptions.IllegalEntityException;
import model.BaseEntity;


import java.util.ArrayList;
import java.util.Collection;

public class EntityIdValidator implements ObjectValidator {
    @Override
    public Collection<Exception> validate(Object objectToValidate) {
        Collection<Exception> exceptions = new ArrayList<>();
        checkId((BaseEntity) objectToValidate, exceptions);
        return exceptions;
    }

    private void checkId(BaseEntity entity, Collection<Exception> exceptions){
        Long id = entity.getId();
        if(id == null){
            exceptions.add(new IllegalEntityException(entity,"id shouldn't be null"));
            return;
        }
        if(id <0L){
            exceptions.add(new IllegalEntityException(entity,"id shouldn't be negative "));
            return;
        }
    }
}
