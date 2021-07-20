package com.artemsilantev.core.mappers.impl.xml;

import com.artemsilantev.core.exceptions.EntityMappingException;
import jakarta.xml.bind.JAXBException;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.utils.JaxbUtils;


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