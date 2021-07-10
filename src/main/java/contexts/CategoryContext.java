package contexts;

import api.data.CategoryDataStorage;
import api.utils.loaders.Loader;
import api.utils.mappers.CategoryMapper;
import api.repositories.CategoryRepository;
import api.services.CategoryService;
import api.utils.validators.ObjectValidator;
import api.utils.validators.TextValidator;
import controllers.CategoryController;
import model.Category;
import utils.loaders.EntityLoader;
import utils.mappers.CategoryMapperImpl;
import repositories.CategoryRepositoryImpl;
import services.CategoryServiceImpl;
import storages.CategoryDataStorageImpl;
import utils.validators.object.CategoryValidator;
import org.modelmapper.TypeToken;
import utils.validators.text.JsonFullTextValidator;

import java.lang.reflect.Type;
import java.util.Collection;

public class CategoryContext {
    public static final String categoryFileName = "categoryData.json";
    public static final ObjectValidator categoryValidator = new CategoryValidator();
    public static final CategoryMapper categoryMapper = new CategoryMapperImpl();
    public static final Type typeTokenCollection = new TypeToken<Collection<Category>>() {
    }.getType();

    private static final Loader categoryLoader = EntityLoader.builder()
            .path(categoryFileName)
            .fileReader(UtilsContext.textFileReader)
            .parser(UtilsContext.gsonParser)
            .textValidators(new TextValidator[]{UtilsContext.jsonTextValidator})
            .objectValidators(new ObjectValidator[]{UtilsContext.entityIdValidator, categoryValidator})
            .typeTokenCollection(typeTokenCollection).build();

    private static final CategoryDataStorage categoryDataStorage = new CategoryDataStorageImpl(categoryLoader);
    private static final CategoryRepository categoryRepository = new CategoryRepositoryImpl(categoryDataStorage);
    private static final CategoryService categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper, UtilsContext.entitySaver);

    public static final CategoryController categoryController = new CategoryController(categoryService);
}

