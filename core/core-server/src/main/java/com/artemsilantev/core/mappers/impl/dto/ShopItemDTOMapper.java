package com.artemsilantev.core.mappers.impl.dto;

import com.artemsilantev.core.dto.CategoryDTO;
import com.artemsilantev.core.dto.ShopItemDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.ShopItem;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ShopItemDTOMapper implements Mapper<ShopItemDTO, ShopItem> {

  private final ModelMapper modelMapper;

  public ShopItemDTOMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    var map = modelMapper.createTypeMap(Category.class, CategoryDTO.class);
    map.addMapping(category -> {
      var parent = category.getParent();
      return parent != null ? parent.getId() : null;
    }, CategoryDTO::setParentId);
  }

  @Override
  public ShopItemDTO toTarget(ShopItem source) {
    return modelMapper.map(source,
        ShopItemDTO.class);
  }

  @Override
  public ShopItem toSource(ShopItemDTO target) {
    return modelMapper.map(target, ShopItem.class);
  }

  @Override
  public Collection<ShopItemDTO> toTargetCollection(Collection<ShopItem> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<ShopItem> toSourceCollection(Collection<ShopItemDTO> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
