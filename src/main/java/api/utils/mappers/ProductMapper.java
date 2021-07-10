package api.utils.mappers;

import dto.CategoryDTO;
import dto.ProductDTO;
import model.Category;
import model.Product;

import java.util.Collection;

public interface ProductMapper {
    ProductDTO map(Product product);
    Collection<ProductDTO> mapCollection(Collection<Product> collection);
}
