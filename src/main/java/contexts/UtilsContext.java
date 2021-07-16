package contexts;

import filemanagers.TextFileManger;
import filemanagers.FileManager;
import validators.Validator;
import validators.entity.EntityIdValidator;
import validators.text.JsonTextValidator;

public class UtilsContext {
    public static final Validator<String> JSON_TEXT_VALIDATOR = new JsonTextValidator();
    public static final Validator ENTITY_ID_VALIDATOR = new EntityIdValidator();
    public static final FileManager TEXT_FILE_MANAGER = new TextFileManger();
}
