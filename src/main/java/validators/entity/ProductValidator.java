package validators.entity;

import org.apache.commons.lang3.StringUtils;
import validators.ObjectValidator;
import lombok.AllArgsConstructor;
import model.Category;
import model.Product;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class ProductValidator implements ObjectValidator {

    @Override
    public Collection<String> validate(Object obj) {

        var problems = new ArrayList<String>();

        if (!(obj instanceof Product)) {
            problems.add("Object should be instance of product");
            return problems;
        }

        var product = (Product) obj;
        problems.addAll(checkName(product.getName()));
        return problems;

    }

    private Collection<String> checkName(String name) {
        var problems = new ArrayList<String>();
        if (StringUtils.isBlank(name)) {
            problems.add("Name shouldn't be blank");
        }
        return problems;
    }
}
