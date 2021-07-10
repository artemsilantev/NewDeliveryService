package utils.validators.object;

import api.utils.validators.ObjectValidator;
import exceptions.IllegalEntityException;
import lombok.AllArgsConstructor;
import model.Category;
import model.Product;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class ProductValidator implements ObjectValidator {

    private final ObjectValidator categoryValidator;
    private final ObjectValidator categoryIdValidator;

    @Override
    public Collection<Exception> validate(Object objectToValidate) {

        Collection<Exception> exceptions = new ArrayList<>();

        if (!(objectToValidate instanceof Product)) {
            exceptions.add(new ClassCastException("Object should be product"));
            return exceptions;
        }

        Product product = (Product) objectToValidate;
        checkName(product, exceptions);
        checkCategory(product, exceptions);
        return exceptions;

    }


    private void checkName(Product product, Collection<Exception> exceptions) {
        String name = product.getName();
        if (name == null || name.trim() == "") {
            exceptions.add(new IllegalEntityException(product, "name shouldn't be null"));
            return;
        }
    }

    private void checkCategory(Product product, Collection<Exception> exceptions) {
        for (Category category : product.getCategories()) {
            if (!categoryValidator.validate(category).isEmpty()) {
                exceptions.add(new IllegalEntityException(product, "problem with categories fields"));
            }
            if (!categoryIdValidator.validate(category).isEmpty()) {
                exceptions.add(new IllegalEntityException(product, "problem with categories id"));
            }

        }
    }
}
