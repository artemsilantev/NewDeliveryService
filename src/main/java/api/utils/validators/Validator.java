package api.utils.validators;

import java.util.Collection;

public interface Validator {
    Collection<Exception> validate(Object objectToValidate);
}
