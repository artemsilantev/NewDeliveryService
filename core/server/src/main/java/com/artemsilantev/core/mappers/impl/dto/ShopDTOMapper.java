package com.artemsilantev.core.mappers.impl.dto;

import com.artemsilantev.core.dto.ShopDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Shop;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ShopDTOMapper implements Mapper<ShopDTO, Shop> {

  private final ModelMapper modelMapper;

  public ShopDTOMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public ShopDTO toTarget(Shop source) {
    return modelMapper.map(source,
        ShopDTO.class);
  }

  @Override
  public Shop toSource(ShopDTO target) {
    return modelMapper.map(target, Shop.class);
  }

  @Override
  public Collection<ShopDTO> toTargetCollection(Collection<Shop> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Shop> toSourceCollection(Collection<ShopDTO> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
