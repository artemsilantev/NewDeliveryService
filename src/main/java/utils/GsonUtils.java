package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GsonUtils {

    private static final Gson gson = new Gson();

    private GsonUtils() {
    }

    public static Object deserialize(String textToParse, Type type) {
        return gson.fromJson(textToParse, type);
    }

    public static String serialize(Object obj) {
        return gson.toJson(obj);
    }

}
