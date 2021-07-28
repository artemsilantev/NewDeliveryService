package com.artemsilantev.core.mapper.impl.dto;

import com.artemsilantev.core.dto.CategoryDto;
import com.artemsilantev.core.dto.ShopItemDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.ShopItem;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ShopItemDtoMapper implements Mapper<ShopItemDto, ShopItem> {

  private final ModelMapper modelMapper;

  public ShopItemDtoMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    var map = modelMapper.createTypeMap(Category.class, CategoryDto.class);
    map.addMapping(category -> {
      var parent = category.getParent();
      return parent != null ? parent.getId() : null;
    }, CategoryDto::setParentId);
  }

  @Override
  public ShopItemDto toTarget(ShopItem source) {
    return modelMapper.map(source,
        ShopItemDto.class);
  }

  @Override
  public ShopItem toSource(ShopItemDto target) {
    return modelMapper.map(target, ShopItem.class);
  }

  @Override
  public Collection<ShopItemDto> toTargetCollection(Collection<ShopItem> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<ShopItem> toSourceCollection(Collection<ShopItemDto> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
