package services.impl;

import dto.ProductDTO;
import mappers.Mapper;
import model.Product;
import repositories.ProductRepository;
import services.ProductService;

public class ProductServiceImpl extends AbstractServiceImpl<ProductDTO, Product>
    implements ProductService {

  public ProductServiceImpl(Mapper<ProductDTO, Product> mapper,
      ProductRepository productRepository) {
    super(mapper, productRepository);
  }

  @Override
  public ProductDTO create(Product entity) {
    for (Product product : abstractRepository.getAll()) {
      if (product.getName().equals(entity.getName())) {
        entity.setId(product.getId());
        return mapperDTO.toTarget(entity);
      }
    }
    entity.setId(abstractRepository.create(entity).getId());
    return mapperDTO.toTarget(entity);
  }
}
