package validators;

import java.util.Collection;

public interface ObjectValidator{
    Collection<String> validate(Object obj);
}
