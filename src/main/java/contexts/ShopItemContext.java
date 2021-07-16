package contexts;

import configs.DataStorageConfiguration;
import dto.ShopItemDTO;
import mappers.Mapper;
import mappers.impl.dto.ShopItemDTOMapper;
import mappers.impl.json.ShopItemJsonMapper;
import model.ShopItem;
import repositories.ShopItemRepository;
import repositories.impl.ShopItemRepositoryImpl;
import services.ShopItemService;
import services.impl.ShopItemServiceImpl;
import storages.ShopItemDataStorage;
import storages.impl.ShopItemDataStorageImpl;
import validators.Validator;

import java.util.Arrays;

public class ShopItemContext {
    public static final String SHOP_ITEM_FILE_NAME = "shopItemData.json";
    public static final Mapper<ShopItem, String> SHOP_ITEM_JSON_MAPPER = new ShopItemJsonMapper();
    public static final Mapper<ShopItemDTO, ShopItem> SHOP_ITEM_DTO_MAPPER = new ShopItemDTOMapper();
    public static final DataStorageConfiguration<ShopItem> SHOP_ITEM_STORAGE_CONFIGURATION = DataStorageConfiguration.<ShopItem>builder()
            .pathToFile(SHOP_ITEM_FILE_NAME)
            .fileManager(UtilsContext.TEXT_FILE_MANAGER)
            .mapper(SHOP_ITEM_JSON_MAPPER)
            .entityValidators(Arrays.asList((Validator<ShopItem>) UtilsContext.ENTITY_ID_VALIDATOR))
            .textValidators(Arrays.asList(UtilsContext.JSON_TEXT_VALIDATOR))
            .build();

    public static final ShopItemDataStorage SHOP_ITEM_DATA_STORAGE = new ShopItemDataStorageImpl(SHOP_ITEM_STORAGE_CONFIGURATION,
            ShopContext.SHOP_DATA_STORAGE, ProductContext.PRODUCT_DATA_STORAGE);
    public static final ShopItemRepository SHOP_ITEM_REPOSITORY = new ShopItemRepositoryImpl(SHOP_ITEM_DATA_STORAGE);
    public static final ShopItemService SHOP_ITEM_SERVICE = new ShopItemServiceImpl(SHOP_ITEM_DTO_MAPPER, SHOP_ITEM_REPOSITORY);

}