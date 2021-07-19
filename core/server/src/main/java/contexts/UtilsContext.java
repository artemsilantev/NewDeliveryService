package contexts;

import filemanagers.FileManager;
import filemanagers.TextFileManger;
import validators.Validator;
import validators.entity.EntityIdValidator;
import validators.text.SimpleTextItemValidator;

public class UtilsContext {

  public static final Validator<String> JSON_TEXT_VALIDATOR = new SimpleTextItemValidator();
  public static final Validator ENTITY_ID_VALIDATOR = new EntityIdValidator();
  public static final FileManager TEXT_FILE_MANAGER = new TextFileManger();
}
