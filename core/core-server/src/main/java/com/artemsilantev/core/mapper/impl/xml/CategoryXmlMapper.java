package com.artemsilantev.core.mapper.impl.xml;

import com.artemsilantev.core.exception.EntityMappingException;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.util.JaxbUtils;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBException;


public class CategoryXmlMapper implements Mapper<Category, String> {

  @Override
  public Category toTarget(String source) {
    try {
      return JaxbUtils.deserialize(source, Category.class);
    } catch (JAXBException jaxbException) {
      throw new EntityMappingException(String.format("Couldn't map from %s to %s: %s",
          '[' + source + ']', "category", jaxbException.getMessage()),
          jaxbException);
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
      throw new EntityMappingException(String.format("Couldn't map from %s to %s: %s",
          '[' + target.toString() + ']', "string", jaxbException.getMessage()),
          jaxbException);
    } finally {
      target.setParent(parentOld);
    }
  }

  @Override
  public Collection<Category> toTargetCollection(Collection<String> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<String> toSourceCollection(Collection<Category> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}