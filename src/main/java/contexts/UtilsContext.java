package contexts;

import filemanagers.TextFileManger;
import validators.ObjectValidator;
import validators.TextValidator;
import filemanagers.FileManager;
import validators.entity.EntityIdValidator;
import validators.text.JsonTextValidator;

public class UtilsContext {
    public static final TextValidator JSON_TEXT_VALIDATOR = new JsonTextValidator();
    public static final ObjectValidator ENTITY_ID_VALIDATOR = new EntityIdValidator();
    public static final FileManager TEXT_FILE_MANAGER = new TextFileManger();
}
