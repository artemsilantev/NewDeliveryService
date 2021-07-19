package contexts;

import configs.DataStorageConfiguration;
import dto.CategoryDTO;
import handlers.Handler;
import handlers.ParserHandler;
import java.util.Arrays;
import mappers.Mapper;
import mappers.impl.dto.CategoryDTOMapper;
import mappers.impl.json.CategoryJsonMapper;
import mappers.impl.xml.CategoryXmlMapper;
import model.Category;
import repositories.CategoryRepository;
import repositories.impl.CategoryRepositoryImpl;
import services.CategoryService;
import services.impl.CategoryServiceImpl;
import storages.CategoryDataStorage;
import storages.impl.CategoryDataStorageImpl;
import validators.Validator;
import validators.entity.CategoryValidator;

public class CategoryContext {

  public static final String CATEGORY_FILE_NAME = "categoryData.xml";
  public static final Validator<Category> CATEGORY_VALIDATOR = new CategoryValidator();
  public static final Mapper<Category, String> CATEGORY_JSON_MAPPER = new CategoryJsonMapper();
  public static final Mapper<Category, String> CATEGORY_XML_MAPPER = new CategoryXmlMapper();
  public static final Handler<Mapper<Category, String>, String> CATEGORY_MAPPER_HANDLER = new ParserHandler<>(
      CATEGORY_JSON_MAPPER, CATEGORY_XML_MAPPER, UtilsContext.TEXT_FILE_MANAGER);
  public static final Mapper<CategoryDTO, Category> CATEGORY_DTO_MAPPER = new CategoryDTOMapper();
  public static final DataStorageConfiguration<Category> CATEGORY_STORAGE_CONFIGURATION = DataStorageConfiguration
      .<Category>builder()
      .setPathToFile(CATEGORY_FILE_NAME)
      .setFileManager(UtilsContext.TEXT_FILE_MANAGER)
      .setMapperHandler(CATEGORY_MAPPER_HANDLER)
      .setEntityValidators(Arrays
          .asList(CATEGORY_VALIDATOR, (Validator<Category>) UtilsContext.ENTITY_ID_VALIDATOR))
      .setTextValidators(Arrays.asList(UtilsContext.JSON_TEXT_VALIDATOR))
      .build();

  public static final CategoryDataStorage CATEGORY_DATA_STORAGE = new CategoryDataStorageImpl(
      CATEGORY_STORAGE_CONFIGURATION);
  public static final CategoryRepository CATEGORY_REPOSITORY = new CategoryRepositoryImpl(
      CATEGORY_DATA_STORAGE);
  public static final CategoryService CATEGORY_SERVICE = new CategoryServiceImpl(
      CATEGORY_DTO_MAPPER, CATEGORY_REPOSITORY);

}