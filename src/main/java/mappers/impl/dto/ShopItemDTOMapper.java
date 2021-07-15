package mappers.impl.dto;

import dto.ShopDTO;
import dto.ShopItemDTO;
import mappers.Mapper;
import model.Shop;
import model.ShopItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ShopItemDTOMapper implements Mapper<ShopItemDTO, ShopItem> {

    private final ModelMapper modelMapper;

    public ShopItemDTOMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public ShopItemDTO toTarget(ShopItem source) {
        return modelMapper.map(source,
                ShopItemDTO.class);
    }

    @Override
    public ShopItem toSource(ShopItemDTO target) {
        return modelMapper.map(target, ShopItem.class);
    }

}
