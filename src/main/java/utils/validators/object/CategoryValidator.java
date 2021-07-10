package utils.validators.object;

import api.utils.validators.ObjectValidator;
import exceptions.IllegalEntityException;
import model.Category;

import java.util.ArrayList;
import java.util.Collection;

public class CategoryValidator implements ObjectValidator {
    @Override
    public Collection<Exception> validate(Object objectToValidate) {
        Collection<Exception> exceptions = new ArrayList<>();

        if (!(objectToValidate instanceof Category)) {
            exceptions.add(new ClassCastException("Object should be category"));
            return exceptions;
        }
        Category category = (Category) objectToValidate;
        checkName(category,exceptions);
        return exceptions;

    }

    private void checkName(Category category, Collection<Exception> exceptions){
        String name = category.getName();
        if(name == null || name.trim() == ""){
            exceptions.add(new IllegalEntityException(category,"name shouldn't be null"));
        }
    }
}
