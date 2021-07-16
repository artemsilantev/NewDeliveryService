package contexts;

import configs.DataStorageConfiguration;
import dto.CategoryDTO;
import mappers.Mapper;
import mappers.impl.dto.CategoryDTOMapper;
import mappers.impl.json.CategoryJsonMapper;
import model.Product;
import repositories.CategoryRepository;
import repositories.impl.CategoryRepositoryImpl;
import services.CategoryService;
import services.impl.CategoryServiceImpl;
import storages.CategoryDataStorage;
import model.Category;
import storages.impl.CategoryDataStorageImpl;
import validators.Validator;
import validators.entity.CategoryValidator;

import java.util.Arrays;

public class CategoryContext {
    public static final String CATEGORY_FILE_NAME = "categoryData.json";
    public static final Validator<Category> CATEGORY_VALIDATOR = new CategoryValidator();
    public static final Mapper<Category, String> CATEGORY_JSON_MAPPER = new CategoryJsonMapper();
    public static final Mapper<CategoryDTO, Category> CATEGORY_DTO_MAPPER = new CategoryDTOMapper();
    public static final DataStorageConfiguration<Category>  CATEGORY_STORAGE_CONFIGURATION = DataStorageConfiguration.<Category> builder()
            .pathToFile(CATEGORY_FILE_NAME)
            .fileManager(UtilsContext.TEXT_FILE_MANAGER)
            .mapper(CATEGORY_JSON_MAPPER)
            .entityValidators(Arrays.asList(CATEGORY_VALIDATOR,  (Validator<Category>) UtilsContext.ENTITY_ID_VALIDATOR))
            .textValidators(Arrays.asList(UtilsContext.JSON_TEXT_VALIDATOR))
            .build();

    public static final CategoryDataStorage CATEGORY_DATA_STORAGE = new CategoryDataStorageImpl(CATEGORY_STORAGE_CONFIGURATION);
    public static final CategoryRepository CATEGORY_REPOSITORY = new CategoryRepositoryImpl(CATEGORY_DATA_STORAGE);
    public static final CategoryService CATEGORY_SERVICE = new CategoryServiceImpl(CATEGORY_DTO_MAPPER, CATEGORY_REPOSITORY);

}