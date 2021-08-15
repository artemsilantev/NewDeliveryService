package com.artemsilantev.core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.artemsilantev.core.util.GsonUtils;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

class GsonUtilsTest {

  @Test
  void testDeserializeNullText() {
    assertNull(GsonUtils.deserialize(null, Object.class));
  }

  @Test
  void testDeserializeNullType() {
    assertThrows(NullPointerException.class, () ->
        GsonUtils.deserialize("{\"id\": \"1\"}", null));
  }


  @Test
  void testDeserializeMap() {
    var textToDeserialize = "{\"id\":\"1\"}";
    var actual = GsonUtils.deserialize(textToDeserialize, HashMap.class);
    assertTrue(actual instanceof HashMap);
  }

  @Test
  void testDeserializeMapWithValue() {
    var textToDeserialize = "{\"id\":\"1\"}";
    var mapType = new TypeToken<HashMap<String, Long>>() {
    }.getType();
    var expected = 1L;
    var actual = (HashMap<String, Long>) GsonUtils.deserialize(textToDeserialize, mapType);
    assertEquals(expected, actual.get("id"));
  }

  @Test
  void testSerializeNull() {
    var actual = GsonUtils.serialize(null);
    assertNull(actual);
  }

  @Test
  void testSerializeEmpty() {
    var actual = GsonUtils.serialize("");
    assertEquals("\"\"", actual);
  }

  @Test
  void testSerializeMap() {
    var map = new HashMap<String, Long>();
    map.put("id", 1L);
    var actual = GsonUtils.serialize(map);
    assertEquals("{\"id\":1}", actual);
  }
}
