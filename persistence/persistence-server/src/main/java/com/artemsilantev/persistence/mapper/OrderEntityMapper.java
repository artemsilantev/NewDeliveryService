package com.artemsilantev.persistence.mapper;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Order;
import com.artemsilantev.persistence.model.OrderEntity;

@org.mapstruct.Mapper(componentModel = "spring", uses = ShopItemEntityMapper.class)
public interface OrderEntityMapper extends Mapper<Order, OrderEntity> {

}
