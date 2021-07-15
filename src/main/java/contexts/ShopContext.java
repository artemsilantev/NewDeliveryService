package contexts;

import configs.DataStorageConfiguration;
import dto.ShopDTO;
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

import java.util.Arrays;

public class ShopContext {
    public static final String SHOP_FILE_NAME = "shopData.json";
    public static final Mapper<Shop, String> SHOP_JSON_MAPPER = new ShopJsonMapper();
    public static final Mapper<ShopDTO, Shop> SHOP_DTO_MAPPER = new ShopDTOMapper();
    public static final DataStorageConfiguration SHOP_STORAGE_CONFIGURATION = DataStorageConfiguration.builder()
            .pathToFile(SHOP_FILE_NAME)
            .fileManager(UtilsContext.TEXT_FILE_MANAGER)
            .mapper(SHOP_JSON_MAPPER)
            .objectValidators(Arrays.asList(UtilsContext.ENTITY_ID_VALIDATOR))
            .textValidators(Arrays.asList(UtilsContext.JSON_TEXT_VALIDATOR))
            .build();

    public static final ShopDataStorage SHOP_DATA_STORAGE = new ShopDataStorageImpl(SHOP_STORAGE_CONFIGURATION);
    public static final ShopRepository SHOP_REPOSITORY = new ShopRepositoryImpl(SHOP_DATA_STORAGE);
    public static final ShopService SHOP_SERVICE = new ShopServiceImpl(SHOP_DTO_MAPPER, SHOP_REPOSITORY);

}