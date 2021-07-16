package contexts;

import configs.DataStorageConfiguration;
import dto.OrderDTO;
import mappers.Mapper;
import mappers.impl.dto.OrderDTOMapper;
import mappers.impl.json.OrderJsonMapper;
import model.Order;
import repositories.OrderRepository;
import repositories.impl.OrderRepositoryImpl;
import services.OrderService;
import services.impl.OrderServiceImpl;
import storages.OrderDataStorage;
import storages.impl.OrderDataStorageImpl;
import validators.Validator;

import java.util.Arrays;

public class OrderContext {
    public static final String ORDER_FILE_NAME = "orderData.json";
    public static final Mapper<Order, String> ORDER_JSON_MAPPER = new OrderJsonMapper();
    public static final Mapper<OrderDTO, Order> ORDER_DTO_MAPPER = new OrderDTOMapper();
    public static final DataStorageConfiguration<Order> ORDER_STORAGE_CONFIGURATION = DataStorageConfiguration.<Order>builder()
            .pathToFile(ORDER_FILE_NAME)
            .fileManager(UtilsContext.TEXT_FILE_MANAGER)
            .mapper(ORDER_JSON_MAPPER)
            .entityValidators(Arrays.asList((Validator<Order>) UtilsContext.ENTITY_ID_VALIDATOR))
            .textValidators(Arrays.asList(UtilsContext.JSON_TEXT_VALIDATOR))
            .build();

    public static final OrderDataStorage ORDER_DATA_STORAGE = new OrderDataStorageImpl(ORDER_STORAGE_CONFIGURATION,
            ShopItemContext.SHOP_ITEM_DATA_STORAGE, UserContext.USER_DATA_STORAGE);
    public static final OrderRepository ORDER_REPOSITORY = new OrderRepositoryImpl(ORDER_DATA_STORAGE);
    public static final OrderService ORDER_SERVICE = new OrderServiceImpl(ORDER_DTO_MAPPER, ORDER_REPOSITORY);

}