package contexts;

import configs.DataStorageConfiguration;
import dto.ProductDTO;
import handlers.Handler;
import handlers.ParserHandler;
import java.util.Arrays;
import mappers.Mapper;
import mappers.impl.dto.ProductDTOMapper;
import mappers.impl.json.ProductJsonMapper;
import model.Product;
import repositories.ProductRepository;
import repositories.impl.ProductRepositoryImpl;
import services.ProductService;
import services.impl.ProductServiceImpl;
import storages.ProductDataStorage;
import storages.impl.ProductDataStorageImpl;
import validators.Validator;
import validators.entity.ProductValidator;

public class ProductContext {

  public static final String PRODUCT_FILE_NAME = "productData.json";
  public static final Validator<Product> PRODUCT_VALIDATOR = new ProductValidator();
  public static final Mapper<Product, String> PRODUCT_JSON_MAPPER = new ProductJsonMapper();
  public static final Mapper<ProductDTO, Product> PRODUCT_DTO_MAPPER = new ProductDTOMapper();
  public static final Handler<Mapper<Product, String>, String> PRODUCT_MAPPER_HANDLER = new ParserHandler<>(
      PRODUCT_JSON_MAPPER, null, UtilsContext.TEXT_FILE_MANAGER);
  public static final DataStorageConfiguration<Product> PRODUCT_STORAGE_CONFIGURATION = DataStorageConfiguration
      .<Product>builder()
      .setPathToFile(PRODUCT_FILE_NAME)
      .setFileManager(UtilsContext.TEXT_FILE_MANAGER)
      .setMapperHandler(PRODUCT_MAPPER_HANDLER)
      .setEntityValidators(
          Arrays.asList(PRODUCT_VALIDATOR, (Validator<Product>) UtilsContext.ENTITY_ID_VALIDATOR))
      .setTextValidators(Arrays.asList(UtilsContext.JSON_TEXT_VALIDATOR))
      .build();

  public static final ProductDataStorage PRODUCT_DATA_STORAGE = new ProductDataStorageImpl(
      PRODUCT_STORAGE_CONFIGURATION, CategoryContext.CATEGORY_DATA_STORAGE);
  public static final ProductRepository PRODUCT_REPOSITORY = new ProductRepositoryImpl(
      PRODUCT_DATA_STORAGE);
  public static final ProductService PRODUCT_SERVICE = new ProductServiceImpl(PRODUCT_DTO_MAPPER,
      PRODUCT_REPOSITORY);

}

