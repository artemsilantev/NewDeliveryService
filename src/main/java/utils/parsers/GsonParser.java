package utils.parsers;

import api.utils.parsers.Parser;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Collection;

public class GsonParser implements Parser {

    private final Gson gson;

    public GsonParser() {
        gson = new Gson();
    }


    @Override
    public Collection deserializeCollection(String textToParse, Type type) {
        return gson.fromJson(textToParse,type);
    }

    @Override
    public <E> String serializeEntity(E entity) {
        return gson.toJson(entity);
    }
}
