package contexts;

import api.data.ProductDataStorage;
import api.repositories.ProductRepository;
import api.services.ProductService;
import api.utils.loaders.Loader;
import api.utils.mappers.ProductMapper;
import api.utils.validators.ObjectValidator;
import api.utils.validators.TextValidator;
import controllers.ProductController;
import model.Product;
import org.modelmapper.TypeToken;
import repositories.ProductRepositoryImpl;
import services.ProductServiceImpl;
import storages.ProductDataStorageImpl;
import utils.loaders.EntityLoader;
import utils.mappers.ProductMapperImpl;
import utils.validators.object.ProductValidator;

import java.lang.reflect.Type;
import java.util.Collection;

public class ProductContext {
    public static final String productFileName="productData.json";
    public static final ObjectValidator productValidator = new ProductValidator(CategoryContext.categoryValidator, UtilsContext.entityIdValidator);
    public static final ProductMapper productMapper = new ProductMapperImpl();
    public static final Type typeTokenCollection = new TypeToken<Collection<Product>>(){}.getType();

    private static final Loader productLoader = EntityLoader.builder()
            .path(productFileName)
            .fileReader(UtilsContext.textFileReader)
            .parser(UtilsContext.gsonParser)
            .textValidators(new TextValidator[]{UtilsContext.jsonTextValidator})
            .objectValidators(new ObjectValidator[]{UtilsContext.entityIdValidator, productValidator})
            .typeTokenCollection(typeTokenCollection).build();


    private static final ProductDataStorage productDataStorage = new ProductDataStorageImpl(productLoader);
    private static final ProductRepository productRepository = new ProductRepositoryImpl(productDataStorage);
    private static final ProductService productService = new ProductServiceImpl(productRepository,productMapper, UtilsContext.entitySaver);

    public static final ProductController productController = new ProductController(productService);
}

