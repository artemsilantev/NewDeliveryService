package com.artemsilantev.core.mapper.impl.dto;

import com.artemsilantev.core.dto.ShopDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Shop;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ShopDtoMapper implements Mapper<ShopDto, Shop> {

  private final ModelMapper modelMapper;

  public ShopDtoMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public ShopDto toTarget(Shop source) {
    return modelMapper.map(source,
        ShopDto.class);
  }

  @Override
  public Shop toSource(ShopDto target) {
    return modelMapper.map(target, Shop.class);
  }

  @Override
  public Collection<ShopDto> toTargetCollection(Collection<Shop> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Shop> toSourceCollection(Collection<ShopDto> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
