package contexts;

import configs.DataStorageConfiguration;
import dto.ShopDTO;
import handlers.Handler;
import handlers.ParserHandler;
import java.util.Arrays;
import mappers.Mapper;
import mappers.impl.dto.ShopDTOMapper;
import mappers.impl.json.ShopJsonMapper;
import model.Shop;
import repositories.ShopRepository;
import repositories.impl.ShopRepositoryImpl;
import services.ShopService;
import services.impl.ShopServiceImpl;
import storages.ShopDataStorage;
import storages.impl.ShopDataStorageImpl;
import validators.Validator;

public class ShopContext {

  public static final String SHOP_FILE_NAME = "shopData.json";
  public static final Mapper<Shop, String> SHOP_JSON_MAPPER = new ShopJsonMapper();
  public static final Mapper<ShopDTO, Shop> SHOP_DTO_MAPPER = new ShopDTOMapper();
  public static final Handler<Mapper<Shop, String>, String> SHOP_MAPPER_HANDLER = new ParserHandler<>(
      SHOP_JSON_MAPPER, null, UtilsContext.TEXT_FILE_MANAGER);
  public static final DataStorageConfiguration<Shop> SHOP_STORAGE_CONFIGURATION = DataStorageConfiguration
      .<Shop>builder()
      .setPathToFile(SHOP_FILE_NAME)
      .setFileManager(UtilsContext.TEXT_FILE_MANAGER)
      .setMapperHandler(SHOP_MAPPER_HANDLER)
      .setEntityValidators(Arrays.asList((Validator<Shop>) UtilsContext.ENTITY_ID_VALIDATOR))
      .setTextValidators(Arrays.asList(UtilsContext.JSON_TEXT_VALIDATOR))
      .build();

  public static final ShopDataStorage SHOP_DATA_STORAGE = new ShopDataStorageImpl(
      SHOP_STORAGE_CONFIGURATION);
  public static final ShopRepository SHOP_REPOSITORY = new ShopRepositoryImpl(SHOP_DATA_STORAGE);
  public static final ShopService SHOP_SERVICE = new ShopServiceImpl(SHOP_DTO_MAPPER,
      SHOP_REPOSITORY);

}