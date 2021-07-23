package com.artemsilantev.core.mappers.impl.dto;

import com.artemsilantev.core.dto.OrderDTO;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.model.Order;
import java.util.Collection;
import java.util.stream.Collectors;
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

  @Override
  public Collection<OrderDTO> toTargetCollection(Collection<Order> sourceCollection) {
    return sourceCollection.stream()
        .map(this::toTarget)
        .collect(Collectors.toList());
  }

  @Override
  public Collection<Order> toSourceCollection(Collection<OrderDTO> targetCollection) {
    return targetCollection.stream()
        .map(this::toSource)
        .collect(Collectors.toList());
  }
}
