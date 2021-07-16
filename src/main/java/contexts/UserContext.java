package contexts;

import configs.DataStorageConfiguration;
import dto.UserDTO;
import mappers.Mapper;
import mappers.impl.dto.UserDTOMapper;
import mappers.impl.json.UserJsonMapper;
import model.User;
import repositories.UserRepository;
import repositories.impl.UserRepositoryImpl;
import services.UserService;
import services.impl.UserServiceImpl;
import storages.UserDataStorage;
import storages.impl.UserDataStorageImpl;
import validators.Validator;

import java.util.Arrays;

public class UserContext {
    public static final String USER_FILE_NAME = "userData.json";
    public static final Mapper<User, String> USER_JSON_MAPPER = new UserJsonMapper();
    public static final Mapper<UserDTO, User> USER_DTO_MAPPER = new UserDTOMapper();
    public static final DataStorageConfiguration<User> USER_STORAGE_CONFIGURATION = DataStorageConfiguration.<User>builder()
            .pathToFile(USER_FILE_NAME)
            .fileManager(UtilsContext.TEXT_FILE_MANAGER)
            .mapper(USER_JSON_MAPPER)
            .entityValidators(Arrays.asList((Validator<User>) UtilsContext.ENTITY_ID_VALIDATOR))
            .textValidators(Arrays.asList(UtilsContext.JSON_TEXT_VALIDATOR))
            .build();

    public static final UserDataStorage USER_DATA_STORAGE = new UserDataStorageImpl(USER_STORAGE_CONFIGURATION);
    public static final UserRepository USER_REPOSITORY = new UserRepositoryImpl(USER_DATA_STORAGE);
    public static final UserService USER_SERVICE = new UserServiceImpl(USER_DTO_MAPPER, USER_REPOSITORY);

}