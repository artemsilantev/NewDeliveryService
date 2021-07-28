package com.artemsilantev.core.validator.fileitem;

import com.artemsilantev.core.validator.Validator;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;

public class SimpleFileItemValidator implements Validator<String> {

  @Override
  public Collection<String> validate(String item) {
    Collection<String> problems = new ArrayList<>();
    if (StringUtils.isBlank(item)) {
      problems.add("Line is blank");
    }
    return problems;
  }
}
