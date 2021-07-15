package mappers.impl.dto;

import dto.CategoryDTO;
import dto.ProductDTO;
import mappers.Mapper;
import model.Category;
import model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ProductDTOMapper implements Mapper<ProductDTO, Product> {

    private final ModelMapper mapper;

    public ProductDTOMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public ProductDTO toTarget(Product product) {
        return mapper.map(product, ProductDTO.class);
    }

    @Override
    public Product toSource(ProductDTO productDTO){
        return mapper.map(productDTO, Product.class);
    }


}
