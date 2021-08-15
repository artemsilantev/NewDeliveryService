package com.artemsilantev.core.handler;

import com.artemsilantev.core.exception.NoHandlerException;
import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.mapper.Mapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParserHandler<T> implements Handler<Mapper<T, String>, String> {

  private final Mapper<T, String> jsonMapper;
  private final Mapper<T, String> xmlMapper;
  private final FileManager fileManager;

  @Override
  public Mapper<T, String> getHandler(String fileName) {
    String extension = fileManager.getExtension(fileName);
    if (extension == null) {
      extension = "";
    }
    switch (extension) {
      case "json": {
        if (jsonMapper == null) {
          throw createNoHandlerException(fileName);
        }
        return jsonMapper;
      }
      case "xml": {
        if (xmlMapper == null) {
          throw createNoHandlerException(fileName);
        }
        return xmlMapper;
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
