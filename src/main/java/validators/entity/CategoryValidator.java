package validators.entity;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import model.Category;
import validators.Validator;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
public class CategoryValidator implements Validator<Category> {
    @Override
    public Collection<String> validate(Category category) {
        var problems = new ArrayList<String>();
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
