package com.artemsilantev.core.handler;

import com.artemsilantev.core.exception.NoHandlerException;
import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.mapper.Mapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParserHandler<T> implements Handler<Mapper<T, String>, String> {

  private final Mapper<T, String> JsonMapper;
  private final Mapper<T, String> XmlMapper;
  private final FileManager fileManager;

  @Override
  public Mapper<T, String> getHandler(String fileName) {
    String extension = fileManager.getExtension(fileName);
    switch (extension) {
      case "json": {
        if (JsonMapper == null) {
          throw createNoHandlerException(fileName);
        }
        return JsonMapper;
      }
      case "xml": {
        if (XmlMapper == null) {
          throw createNoHandlerException(fileName);
        }
        return XmlMapper;
      }
      default: {
        throw createNoHandlerException(fileName);
      }
    }
  }

  private NoHandlerException createNoHandlerException(String fileName) {
    return new NoHandlerException(
        String.format("Couldn't' find mapper for this file %s", fileName));
  }
}
