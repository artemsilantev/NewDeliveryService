package mappers.impl.json;

import mappers.Mapper;
import model.Category;
import utils.GsonUtils;

public class CategoryJsonMapper implements Mapper<Category, String> {

  @Override
  public Category toTarget(String source) {
    return (Category) GsonUtils.deserialize(source, Category.class);
  }

  @Override
  public String toSource(Category target) {
    var parentOld = target.getParent();
    if (parentOld != null) {
      var parentNew = new Category();
      parentNew.setId(parentOld.getId());
      target.setParent(parentNew);
    }
    String result = GsonUtils.serialize(target);
    target.setParent(parentOld);
    return result;
  }
}
