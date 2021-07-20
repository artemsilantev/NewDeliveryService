package com.artemsilantev.core;

import com.artemsilantev.core.filemanagers.FileManager;
import com.artemsilantev.core.filemanagers.TextFileManger;
import com.artemsilantev.core.handlers.Handler;
import com.artemsilantev.core.handlers.ParserHandler;
import com.artemsilantev.core.mappers.Mapper;
import com.artemsilantev.core.mappers.impl.dto.CategoryDTOMapper;
import com.artemsilantev.core.mappers.impl.dto.OrderDTOMapper;
import com.artemsilantev.core.mappers.impl.dto.ProductDTOMapper;
import com.artemsilantev.core.mappers.impl.dto.ShopDTOMapper;
import com.artemsilantev.core.mappers.impl.dto.ShopItemDTOMapper;
import com.artemsilantev.core.mappers.impl.dto.UserDTOMapper;
import com.artemsilantev.core.mappers.impl.json.CategoryJsonMapper;
import com.artemsilantev.core.mappers.impl.json.OrderJsonMapper;
import com.artemsilantev.core.mappers.impl.json.ProductJsonMapper;
import com.artemsilantev.core.mappers.impl.json.ShopItemJsonMapper;
import com.artemsilantev.core.mappers.impl.json.ShopJsonMapper;
import com.artemsilantev.core.mappers.impl.json.UserJsonMapper;
import com.artemsilantev.core.mappers.impl.xml.CategoryXmlMapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.repositories.CategoryRepository;
import com.artemsilantev.core.repositories.OrderRepository;
import com.artemsilantev.core.repositories.ProductRepository;
import com.artemsilantev.core.repositories.ShopItemRepository;
import com.artemsilantev.core.repositories.ShopRepository;
import com.artemsilantev.core.repositories.UserRepository;
import com.artemsilantev.core.repositories.impl.CategoryRepositoryImpl;
import com.artemsilantev.core.repositories.impl.OrderRepositoryImpl;
import com.artemsilantev.core.repositories.impl.ProductRepositoryImpl;
import com.artemsilantev.core.repositories.impl.ShopItemRepositoryImpl;
import com.artemsilantev.core.repositories.impl.ShopRepositoryImpl;
import com.artemsilantev.core.repositories.impl.UserRepositoryImpl;
import com.artemsilantev.core.services.CategoryService;
import com.artemsilantev.core.services.OrderService;
import com.artemsilantev.core.services.ProductService;
import com.artemsilantev.core.services.ShopItemService;
import com.artemsilantev.core.services.ShopService;
import com.artemsilantev.core.services.UserService;
import com.artemsilantev.core.services.impl.CategoryServiceImpl;
import com.artemsilantev.core.services.impl.OrderServiceImpl;
import com.artemsilantev.core.services.impl.ProductServiceImpl;
import com.artemsilantev.core.services.impl.ShopItemServiceImpl;
import com.artemsilantev.core.services.impl.ShopServiceImpl;
import com.artemsilantev.core.services.impl.UserServiceImpl;
import com.artemsilantev.core.storages.CategoryDataStorage;
import com.artemsilantev.core.storages.OrderDataStorage;
import com.artemsilantev.core.storages.ProductDataStorage;
import com.artemsilantev.core.storages.ShopDataStorage;
import com.artemsilantev.core.storages.ShopItemDataStorage;
import com.artemsilantev.core.storages.UserDataStorage;
import com.artemsilantev.core.storages.impl.CategoryDataStorageImpl;
import com.artemsilantev.core.storages.impl.OrderDataStorageImpl;
import com.artemsilantev.core.storages.impl.ProductDataStorageImpl;
import com.artemsilantev.core.storages.impl.ShopDataStorageImpl;
import com.artemsilantev.core.storages.impl.ShopItemDataStorageImpl;
import com.artemsilantev.core.storages.impl.UserDataStorageImpl;
import com.artemsilantev.core.validators.Validator;
import com.artemsilantev.core.validators.entity.CategoryValidator;
import com.artemsilantev.core.validators.entity.EntityIdValidator;
import com.artemsilantev.core.validators.entity.ProductValidator;
import com.artemsilantev.core.validators.text.SimpleTextItemValidator;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.artemsilantev.core"})
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
  public ShopRepository getShopRepository(ShopDataStorage shopDataStorage) {
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
  public OrderService getOrderService(OrderRepository orderRepository) {
    return new OrderServiceImpl(new OrderDTOMapper(), orderRepository);
  }
}
