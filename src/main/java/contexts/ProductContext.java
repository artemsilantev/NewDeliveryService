package contexts;

import configs.DataStorageConfiguration;
import dto.ProductDTO;
import mappers.Mapper;
import mappers.impl.dto.ProductDTOMapper;
import mappers.impl.json.ProductJsonMapper;
import storages.ProductDataStorage;
import repositories.ProductRepository;
import services.ProductService;
import validators.ObjectValidator;
import model.Product;
import repositories.impl.ProductRepositoryImpl;
import services.impl.ProductServiceImpl;
import storages.impl.ProductDataStorageImpl;
import validators.entity.ProductValidator;

import java.util.Arrays;

public class ProductContext {
    public static final String PRODUCT_FILE_NAME = "productData.json";
    public static final ObjectValidator PRODUCT_VALIDATOR = new ProductValidator();
    public static final Mapper<Product, String> PRODUCT_JSON_MAPPER = new ProductJsonMapper();
    public static final Mapper<ProductDTO, Product> PRODUCT_DTO_MAPPER = new ProductDTOMapper();
    public static final DataStorageConfiguration PRODUCT_STORAGE_CONFIGURATION = DataStorageConfiguration.builder()
            .pathToFile(PRODUCT_FILE_NAME)
            .fileManager(UtilsContext.TEXT_FILE_MANAGER)
            .mapper(PRODUCT_JSON_MAPPER)
            .objectValidators(Arrays.asList(UtilsContext.ENTITY_ID_VALIDATOR, PRODUCT_VALIDATOR))
            .textValidators(Arrays.asList(UtilsContext.JSON_TEXT_VALIDATOR))
            .build();

    public static final ProductDataStorage PRODUCT_DATA_STORAGE = new ProductDataStorageImpl(PRODUCT_STORAGE_CONFIGURATION,
            CategoryContext.CATEGORY_DATA_STORAGE);
    public static final ProductRepository PRODUCT_REPOSITORY = new ProductRepositoryImpl(PRODUCT_DATA_STORAGE);
    public static final ProductService PRODUCT_SERVICE = new ProductServiceImpl(PRODUCT_DTO_MAPPER, PRODUCT_REPOSITORY);

}

