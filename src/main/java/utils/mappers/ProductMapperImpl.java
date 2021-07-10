package utils.mappers;

import api.utils.mappers.CategoryMapper;
import api.utils.mappers.ProductMapper;
import dto.CategoryDTO;
import dto.ProductDTO;
import model.Category;
import model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;

public class ProductMapperImpl implements ProductMapper {

    private final ModelMapper mapper;

    public ProductMapperImpl() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public ProductDTO map(Product product) {
        return mapper.map(product, ProductDTO.class);
    }

    @Override
    public Collection<ProductDTO> mapCollection(Collection<Product> collection) {
        return mapper.map(collection, new TypeToken<Collection<ProductDTO>>() {
        }.getType());
    }


}
