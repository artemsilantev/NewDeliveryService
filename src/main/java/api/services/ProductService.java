package api.services;

import dto.ProductDTO;
import exceptions.NoRecordException;
import model.Product;

import java.util.Collection;

public interface ProductService {
    ProductDTO get(Long id) throws NoRecordException;
    ProductDTO create(Product entity);
    Collection<ProductDTO> getAll();
    void delete(Long id) throws NoRecordException;
    void save();
}
