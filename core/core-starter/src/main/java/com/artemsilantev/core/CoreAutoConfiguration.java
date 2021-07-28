package com.artemsilantev.core;

import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.filemanager.TextFileManger;
import com.artemsilantev.core.handler.Handler;
import com.artemsilantev.core.handler.ParserHandler;
import com.artemsilantev.core.mapper.Mapper;
import com.artemsilantev.core.mapper.impl.dto.CategoryDtoMapper;
import com.artemsilantev.core.mapper.impl.dto.OrderDtoMapper;
import com.artemsilantev.core.mapper.impl.dto.ProductDtoMapper;
import com.artemsilantev.core.mapper.impl.dto.ShopDtoMapper;
import com.artemsilantev.core.mapper.impl.dto.ShopItemDtoMapper;
import com.artemsilantev.core.mapper.impl.dto.UserDtoMapper;
import com.artemsilantev.core.mapper.impl.json.CategoryJsonMapper;
import com.artemsilantev.core.mapper.impl.json.OrderJsonMapper;
import com.artemsilantev.core.mapper.impl.json.ProductJsonMapper;
import com.artemsilantev.core.mapper.impl.json.ShopItemJsonMapper;
import com.artemsilantev.core.mapper.impl.json.ShopJsonMapper;
import com.artemsilantev.core.mapper.impl.json.UserJsonMapper;
import com.artemsilantev.core.mapper.impl.xml.CategoryXmlMapper;
import com.artemsilantev.core.model.Category;
import com.artemsilantev.core.model.Order;
import com.artemsilantev.core.model.Product;
import com.artemsilantev.core.model.Shop;
import com.artemsilantev.core.model.ShopItem;
import com.artemsilantev.core.model.User;
import com.artemsilantev.core.repository.CategoryRepository;
import com.artemsilantev.core.repository.OrderRepository;
import com.artemsilantev.core.repository.ProductRepository;
import com.artemsilantev.core.repository.ShopItemRepository;
import com.artemsilantev.core.repository.ShopRepository;
import com.artemsilantev.core.repository.UserRepository;
import com.artemsilantev.core.repository.impl.CategoryRepositoryImpl;
import com.artemsilantev.core.repository.impl.OrderRepositoryImpl;
import com.artemsilantev.core.repository.impl.ProductRepositoryImpl;
import com.artemsilantev.core.repository.impl.ShopItemRepositoryImpl;
import com.artemsilantev.core.repository.impl.ShopRepositoryImpl;
import com.artemsilantev.core.repository.impl.UserRepositoryImpl;
import com.artemsilantev.core.service.CategoryService;
import com.artemsilantev.core.service.OrderService;
import com.artemsilantev.core.service.ProductService;
import com.artemsilantev.core.service.ShopItemService;
import com.artemsilantev.core.service.ShopService;
import com.artemsilantev.core.service.UserService;
import com.artemsilantev.core.service.impl.CategoryServiceImpl;
import com.artemsilantev.core.service.impl.OrderServiceImpl;
import com.artemsilantev.core.service.impl.ProductServiceImpl;
import com.artemsilantev.core.service.impl.ShopItemServiceImpl;
import com.artemsilantev.core.service.impl.ShopServiceImpl;
import com.artemsilantev.core.service.impl.UserServiceImpl;
import com.artemsilantev.core.storage.CategoryDataStorage;
import com.artemsilantev.core.storage.OrderDataStorage;
import com.artemsilantev.core.storage.ProductDataStorage;
import com.artemsilantev.core.storage.ShopDataStorage;
import com.artemsilantev.core.storage.ShopItemDataStorage;
import com.artemsilantev.core.storage.UserDataStorage;
import com.artemsilantev.core.storage.impl.CategoryDataStorageImpl;
import com.artemsilantev.core.storage.impl.OrderDataStorageImpl;
import com.artemsilantev.core.storage.impl.ProductDataStorageImpl;
import com.artemsilantev.core.storage.impl.ShopDataStorageImpl;
import com.artemsilantev.core.storage.impl.ShopItemDataStorageImpl;
import com.artemsilantev.core.storage.impl.UserDataStorageImpl;
import com.artemsilantev.core.validator.Validator;
import com.artemsilantev.core.validator.entity.CategoryValidator;
import com.artemsilantev.core.validator.entity.EntityIdValidator;
import com.artemsilantev.core.validator.entity.ProductValidator;
import com.artemsilantev.core.validator.fileitem.SimpleFileItemValidator;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.artemsilantev.core"})
public class CoreAutoConfiguration {

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
        .asList(new SimpleFileItemValidator());
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
    return new CategoryServiceImpl(new CategoryDtoMapper(), categoryRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public ProductDataStorage getProductDataStorage(FileManager fileManager,
      CategoryDataStorage categoryDataStorage) {
    Handler<Mapper<Product, String>, String> productParserHandler = new ParserHandler<>(
        new ProductJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleFileItemValidator());
    Collection<Validator<Product>> entityValidators = Arrays
        .asList(new ProductValidator(), new EntityIdValidator<>());
    return new ProductDataStorageImpl(productParserHandler, fileManager, PRODUCT_FILE_NAME,
        parseItemValidators, entityValidators, categoryDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public ProductRepository getProductRepository(ProductDataStorage productDataStorage,
      CategoryRepository categoryRepository) {
    return new ProductRepositoryImpl(productDataStorage, categoryRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public ProductService getProductService(ProductRepository productRepository) {
    return new ProductServiceImpl(new ProductDtoMapper(), productRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopDataStorage getShopDataStorage(FileManager fileManager) {
    Handler<Mapper<Shop, String>, String> shopParserHandler = new ParserHandler<>(
        new ShopJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleFileItemValidator());
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
    return new ShopServiceImpl(new ShopDtoMapper(), shopRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopItemDataStorage getShopItemDataStorage(FileManager fileManager,
      ShopDataStorage shopDataStorage, ProductDataStorage productDataStorage) {
    Handler<Mapper<ShopItem, String>, String> shopItemParserHandler = new ParserHandler<>(
        new ShopItemJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleFileItemValidator());
    Collection<Validator<ShopItem>> entityValidators = Arrays
        .asList(new EntityIdValidator<>());
    return new ShopItemDataStorageImpl(shopItemParserHandler, fileManager, SHOP_ITEM_FILE_NAME,
        parseItemValidators, entityValidators, shopDataStorage, productDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopItemRepository getShopItemRepository(ShopItemDataStorage shopItemDataStorage,
      ShopRepository shopRepository, ProductRepository productRepository) {
    return new ShopItemRepositoryImpl(shopItemDataStorage, shopRepository, productRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public ShopItemService getShopItemService(ShopItemRepository shopItemRepository) {
    return new ShopItemServiceImpl(new ShopItemDtoMapper(), shopItemRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserDataStorage getUserDataStorage(FileManager fileManager) {
    Handler<Mapper<User, String>, String> userParserHandler = new ParserHandler<>(
        new UserJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleFileItemValidator());
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
    return new UserServiceImpl(new UserDtoMapper(), userRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public OrderDataStorage getOrderDataStorage(FileManager fileManager,
      ShopItemDataStorage shopItemDataStorage, UserDataStorage userDataStorage) {
    Handler<Mapper<Order, String>, String> orderParserHandler = new ParserHandler<>(
        new OrderJsonMapper(), null, fileManager);
    Collection<Validator<String>> parseItemValidators = Arrays
        .asList(new SimpleFileItemValidator());
    Collection<Validator<Order>> entityValidators = Arrays
        .asList(new EntityIdValidator<>());
    return new OrderDataStorageImpl(orderParserHandler, fileManager, ORDER_FILE_NAME,
        parseItemValidators, entityValidators, shopItemDataStorage, userDataStorage);
  }

  @Bean
  @ConditionalOnMissingBean
  public OrderRepository getOrderRepository(OrderDataStorage orderDataStorage,
      UserRepository userRepository, ShopItemRepository shopItemRepository) {
    return new OrderRepositoryImpl(orderDataStorage, userRepository, shopItemRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public OrderService getOrderService(OrderRepository orderRepository) {
    return new OrderServiceImpl(new OrderDtoMapper(), orderRepository);
  }
}
