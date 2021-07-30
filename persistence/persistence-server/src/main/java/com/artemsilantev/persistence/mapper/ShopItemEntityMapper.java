package com.artemsilantev.persistence.mapper;

import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.persistence.model.ShopItemEntity;

@org.mapstruct.Mapper(componentModel = "spring", uses = {ShopEntityMapper.class,
    UserEntityMapper.class})
public interface ShopItemEntityMapper extends Mapper<ShopItem, ShopItemEntity> {

}
