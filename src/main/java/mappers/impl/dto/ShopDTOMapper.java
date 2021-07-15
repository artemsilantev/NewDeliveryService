package mappers.impl.dto;

import dto.ShopDTO;
import mappers.Mapper;
import model.Shop;
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
