package com.artemsilantev.core.util;

import com.google.gson.Gson;
import java.lang.reflect.Type;

public class GsonUtils {

  private static final Gson gson = new Gson();

  private GsonUtils() {
  }

  public static Object deserialize(String textToParse, Type type) {
    if (type == null) {
      return "{}";
    }
    return gson.fromJson(textToParse, type);
  }

  public static String serialize(Object obj) {
    String result = gson.toJson(obj);
    if ("null".equals(result)) {
      return null;
    }
    return gson.toJson(obj);
  }

}
