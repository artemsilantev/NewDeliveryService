package com.artemsilantev.core.handlers;

import com.artemsilantev.core.exceptions.NoHandlerException;
import com.artemsilantev.core.filemanagers.FileManager;
import lombok.AllArgsConstructor;
import com.artemsilantev.core.mappers.Mapper;

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
          throw createNoHandlerException(extension);
        }
        return JsonMapper;
      }
      case "xml": {
        if (XmlMapper == null) {
          throw createNoHandlerException(extension);
        }
        return XmlMapper;
      }
      default: {
        throw createNoHandlerException(extension);
      }
    }
  }

  private NoHandlerException createNoHandlerException(String extension) {
    return new NoHandlerException(
        String.format("Couldn't' find mapper for this extension '%s'", extension));
  }
}