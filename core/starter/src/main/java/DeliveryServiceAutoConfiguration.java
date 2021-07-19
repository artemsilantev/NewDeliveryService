
import filemanagers.FileManager;
import filemanagers.TextFileManger;
import handlers.Handler;
import handlers.ParserHandler;
import java.util.Arrays;
import java.util.Collection;
import mappers.Mapper;
import mappers.impl.dto.CategoryDTOMapper;
import mappers.impl.dto.OrderDTOMapper;
import mappers.impl.dto.ProductDTOMapper;
import mappers.impl.dto.ShopDTOMapper;
import mappers.impl.dto.ShopItemDTOMapper;
import mappers.impl.dto.UserDTOMapper;
import mappers.impl.json.CategoryJsonMapper;
import mappers.impl.json.OrderJsonMapper;
import mappers.impl.json.ProductJsonMapper;
import mappers.impl.json.ShopItemJsonMapper;
import mappers.impl.json.ShopJsonMapper;
import mappers.impl.json.UserJsonMapper;
import mappers.impl.xml.CategoryXmlMapper;
import model.Category;
import model.Order;
import model.Product;
import model.Shop;
import model.ShopItem;
import model.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import repositories.CategoryRepository;
import repositories.OrderRepository;
import repositories.ProductRepository;
import repositories.ShopItemRepository;
import repositories.ShopRepository;
import repositories.UserRepository;
import repositories.impl.CategoryRepositoryImpl;
import repositories.impl.OrderRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;
import repositories.impl.ShopItemRepositoryImpl;
import repositories.impl.ShopRepositoryImpl;
import repositories.impl.UserRepositoryImpl;
import services.CategoryService;
import services.OrderService;
import services.ProductService;
import services.ShopItemService;
import services.ShopService;
import services.UserService;
import services.impl.CategoryServiceImpl;
import services.impl.OrderServiceImpl;
import services.impl.ProductServiceImpl;
import services.impl.ShopItemServiceImpl;
import services.impl.ShopServiceImpl;
import services.impl.UserServiceImpl;
import storages.CategoryDataStorage;
import storages.OrderDataStorage;
import storages.ProductDataStorage;
import storages.ShopDataStorage;
import storages.ShopItemDataStorage;
import storages.UserDataStorage;
import storages.impl.CategoryDataStorageImpl;
import storages.impl.OrderDataStorageImpl;
import storages.impl.ProductDataStorageImpl;
import storages.impl.ShopDataStorageImpl;
import storages.impl.ShopItemDataStorageImpl;
import storages.impl.UserDataStorageImpl;
import validators.Validator;
import validators.entity.CategoryValidator;
import validators.entity.EntityIdValidator;
import validators.entity.ProductValidator;
import validators.text.SimpleTextItemValidator;

@Configuration
public class DeliveryServiceAutoConfiguration {

  private static final String CATEGORY_FILE_NAME = "categoryData.xml";
  private static final String ORDER_FILE_NAME = "orderData.json";
  private static final String PRODUCT_FILE_NAME = "productData.json";
  private static final String SHOP_FILE_NAME = "shopData.json";
  private static final String SHOP_ITEM_FILE_NAME = "shopItemData.json";
  private static final String USER_FILE_NAME = "userData.json";

  @Bean
  @ConditionalOnMissingBean
  public FileManager getFileManager() {
    return new TextFileManger();
  }

  @Bean
  @ConditionalOnMissingBean
  public CategoryDataStorage getCategoryDataStorage(FileManager fileManager) {
    Handler<Mapper<Category, String>, String> categoryMapperHandler = new ParserHandler<>(
        new CategoryJsonMapper(), new CategoryXmlMapper(), fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleTextItemValidator());
    Collection<Validator<Category>> entityValidators = Arrays
        .asList(new CategoryValidator(), new EntityIdValidator<>());
    return new CategoryDataStorageImpl(categoryMapperHandler, fileManager, CATEGORY_FILE_NAME,
        parseItemValidators, entityValidators);
  }

  @Bean
  @ConditionalOnMissingBean
  public CategoryRepository getCategoryRepository(CategoryDataStorage categoryDataStorage) {
    return new CategoryRepositoryImpl(categoryDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public CategoryService getCategoryService(CategoryRepository categoryRepository) {
    return new CategoryServiceImpl(new CategoryDTOMapper(), categoryRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public ProductDataStorage getProductDataStorage(FileManager fileManager,
      CategoryDataStorage categoryDataStorage) {
    Handler<Mapper<Product, String>, String> productParserHandler = new ParserHandler<>(
        new ProductJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleTextItemValidator());
    Collection<Validator<Product>> entityValidators = Arrays
        .asList(new ProductValidator(), new EntityIdValidator<>());
    return new ProductDataStorageImpl(productParserHandler, fileManager, PRODUCT_FILE_NAME,
        parseItemValidators, entityValidators, categoryDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public ProductRepository getProductRepository(ProductDataStorage productDataStorage) {
    return new ProductRepositoryImpl(productDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public ProductService getProductService(ProductRepository productRepository) {
    return new ProductServiceImpl(new ProductDTOMapper(), productRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopDataStorage getShopDataStorage(FileManager fileManager) {
    Handler<Mapper<Shop, String>, String> shopParserHandler = new ParserHandler<>(
        new ShopJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleTextItemValidator());
    Collection<Validator<Shop>> entityValidators = Arrays
        .asList(new EntityIdValidator<>());
    return new ShopDataStorageImpl(shopParserHandler, fileManager, SHOP_FILE_NAME,
        parseItemValidators, entityValidators);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopRepository getProductRepository(ShopDataStorage shopDataStorage) {
    return new ShopRepositoryImpl(shopDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopService getShopService(ShopRepository shopRepository) {
    return new ShopServiceImpl(new ShopDTOMapper(), shopRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopItemDataStorage getShopItemDataStorage(FileManager fileManager,
      ShopDataStorage shopDataStorage, ProductDataStorage productDataStorage) {
    Handler<Mapper<ShopItem, String>, String> shopItemParserHandler = new ParserHandler<>(
        new ShopItemJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleTextItemValidator());
    Collection<Validator<ShopItem>> entityValidators = Arrays
        .asList(new EntityIdValidator<>());
    return new ShopItemDataStorageImpl(shopItemParserHandler, fileManager, SHOP_ITEM_FILE_NAME,
        parseItemValidators, entityValidators, shopDataStorage, productDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopItemRepository getShopItemRepository(ShopItemDataStorage shopItemDataStorage) {
    return new ShopItemRepositoryImpl(shopItemDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopItemService getShopItemService(ShopItemRepository shopItemRepository) {
    return new ShopItemServiceImpl(new ShopItemDTOMapper(), shopItemRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserDataStorage getUserDataStorage(FileManager fileManager) {
    Handler<Mapper<User, String>, String> userParserHandler = new ParserHandler<>(
        new UserJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleTextItemValidator());
    Collection<Validator<User>> entityValidators = Arrays
        .asList(new EntityIdValidator<>());
    return new UserDataStorageImpl(userParserHandler, fileManager, USER_FILE_NAME,
        parseItemValidators, entityValidators);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserRepository getUserRepository(UserDataStorage userDataStorage) {
    return new UserRepositoryImpl(userDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserService getUserService(UserRepository userRepository) {
    return new UserServiceImpl(new UserDTOMapper(), userRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public OrderDataStorage getOrderDataStorage(FileManager fileManager,
      ShopItemDataStorage shopItemDataStorage, UserDataStorage userDataStorage) {
    Handler<Mapper<Order, String>, String> orderParserHandler = new ParserHandler<>(
        new OrderJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleTextItemValidator());
    Collection<Validator<Order>> entityValidators = Arrays
        .asList(new EntityIdValidator<>());
    return new OrderDataStorageImpl(orderParserHandler, fileManager, ORDER_FILE_NAME,
        parseItemValidators, entityValidators, shopItemDataStorage, userDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public OrderRepository getOrderRepository(OrderDataStorage orderDataStorage){
    return new OrderRepositoryImpl(orderDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public OrderService getOrderService(OrderRepository orderRepository){
    return new OrderServiceImpl(new OrderDTOMapper(),orderRepository);}
}
