package services;

import api.repositories.ProductRepository;
import api.services.ProductService;
import api.utils.mappers.ProductMapper;
import api.utils.savers.Saver;
import contexts.ProductContext;
import dto.ProductDTO;
import exceptions.NoRecordException;
import model.Product;

import java.util.Collection;

public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final Saver saver;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper mapper, Saver saver) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.saver = saver;
    }

    @Override
    public ProductDTO get(Long id) throws NoRecordException {
        return mapper.map(productRepository.get(id).orElseThrow(()->new NoRecordException("product",id)));
    }

    @Override
    public ProductDTO create(Product entity) {
        for(Product product: productRepository.getAll()){
            if(product.getName().equals(entity.getName())) {
                entity.setId(product.getId());
                return mapper.map(entity);
            }
        }
        entity.setId(productRepository.create(entity).getId());
        return mapper.map(entity);
    }
    @Override
    public Collection<ProductDTO> getAll() {
        return mapper.mapCollection(productRepository.getAll());
    }

    @Override
    public void save() {
        saver.save(productRepository.getAll(), ProductContext.productFileName);
    }

    @Override
    public void delete(Long id) throws NoRecordException {
        productRepository.delete(id);
    }
}
