package com.artemsilantev.core.mapper.impl.dto;

import com.artemsilantev.core.dto.OrderDto;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Order;
import java.util.Collection;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class OrderDtoMapper implements Mapper<OrderDto, Order> {

  private final ModelMapper modelMapper;

  public OrderDtoMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public OrderDto toTarget(Order source) {
    return modelMapper.map(source,
        OrderDto.class);
  }

  @Override
  public Order toSource(OrderDto target) {
    return modelMapper.map(target, Order.class);
  }

  @Override
  public Collection<OrderDto> toTargetCollection(Collection<Order> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Order> toSourceCollection(Collection<OrderDto> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
