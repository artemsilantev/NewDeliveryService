package contexts;

import api.utils.parsers.Parser;
import api.utils.readers.FileReader;
import api.utils.savers.Saver;
import api.utils.validators.ObjectValidator;
import api.utils.validators.TextValidator;
import api.utils.writers.FileWriter;
import utils.parsers.GsonParser;
import utils.readers.TextFileReader;
import utils.savers.EntitySaver;
import utils.validators.object.EntityIdValidator;
import utils.validators.text.JsonFullTextValidator;
import utils.writers.TextFileWriter;

public class UtilsContext {

    public final static TextValidator jsonTextValidator = new JsonFullTextValidator();
    public final static ObjectValidator entityIdValidator = new EntityIdValidator();
    public final static FileReader textFileReader = new TextFileReader();
    public final static FileWriter textFileWriter = new TextFileWriter();
    public final static Parser gsonParser = new GsonParser();
    public final static Saver entitySaver = new EntitySaver(gsonParser,  textFileWriter);

}
