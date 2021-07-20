package com.artemsilantev.core.mappers.impl.dto;

import com.artemsilantev.core.dto.ShopDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Shop;
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

}
