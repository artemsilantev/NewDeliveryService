package com.artemsilantev.persistence.mapper;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.persistence.model.ShopEntity;

@org.mapstruct.Mapper(componentModel = "spring")
public interface ShopEntityMapper extends Mapper<Shop, ShopEntity> {

}
