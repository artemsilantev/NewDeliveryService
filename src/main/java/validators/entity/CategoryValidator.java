package validators.entity;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import validators.ObjectValidator;
import model.Category;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class CategoryValidator implements ObjectValidator {
    @Override
    public Collection<String> validate(Object obj) {
        var problems = new ArrayList<String>();
        if (!(obj instanceof Category)) {
            problems.add("Object should be instance of category");
            return problems;
        }
        var category = (Category) obj;
        problems.addAll(checkName(category.getName()));
        return problems;

    }

    private Collection<String> checkName(String name){
        var problems = new ArrayList<String>();
        if(StringUtils.isBlank(name)){
            problems.add("Name shouldn't be blank");
        }
        return problems;
    }
}
