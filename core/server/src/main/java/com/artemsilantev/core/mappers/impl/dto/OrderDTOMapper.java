package com.artemsilantev.core.mappers.impl.dto;

import com.artemsilantev.core.dto.OrderDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class OrderDTOMapper implements Mapper<OrderDTO, Order> {

  private final ModelMapper modelMapper;

  public OrderDTOMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public OrderDTO toTarget(Order source) {
    return modelMapper.map(source,
        OrderDTO.class);
  }

  @Override
  public Order toSource(OrderDTO target) {
    return modelMapper.map(target, Order.class);
  }

}
