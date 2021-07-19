package validators.text;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import validators.Validator;

public class SimpleTextItemValidator implements Validator<String> {

  @Override
  public Collection<String> validate(String item) {
    Collection<String> problems = new ArrayList<>();
    if (StringUtils.isBlank(item)) {
      problems.add("Line is blank");
    }
    return problems;
  }
}
