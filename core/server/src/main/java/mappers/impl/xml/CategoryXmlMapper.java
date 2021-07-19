package mappers.impl.xml;

import exceptions.EntityMappingException;
import jakarta.xml.bind.JAXBException;
import mappers.Mapper;
import model.Category;
import utils.JaxbUtils;


public class CategoryXmlMapper implements Mapper<Category, String> {

  @Override
  public Category toTarget(String source) {
    try {
      return JaxbUtils.deserialize(source, Category.class);
    } catch (JAXBException jaxbException) {
      throw new EntityMappingException('[' + source + ']', "category", jaxbException.getMessage());
    }
  }

  @Override
  public String toSource(Category target) {
    var parentOld = target.getParent();
    if (parentOld != null) {
      var parentNew = new Category();
      parentNew.setId(parentOld.getId());
      target.setParent(parentNew);
    }
    try {
      return JaxbUtils.serialize(target, "category", Category.class);
    } catch (JAXBException jaxbException) {
      throw new EntityMappingException('[' + target.toString() + ']', "string",
          jaxbException.getMessage());
    } finally {
      target.setParent(parentOld);
    }
  }
}