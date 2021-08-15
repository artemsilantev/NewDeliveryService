package com.artemsilantev.core.handler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.artemsilantev.core.exception.NoHandlerException;
import com.artemsilantev.core.filemanager.FileManager;
import com.artemsilantev.core.mapper.Mapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParserHandlerTest {

  @Spy
  @InjectMocks
  ParserHandler<Object> parserHandler;
  @Mock
  Mapper<Object, String> jsonMapper;
  @Mock
  Mapper<Object, String> xmlMapper;
  @Mock
  FileManager fileManager;

  @ParameterizedTest
  @NullAndEmptySource
  void testGetHandlerNullAndEmptyFileName(String fileName) {
    when(fileManager.getExtension(any()))
        .thenReturn(fileName);
    assertThrows(NoHandlerException.class, () ->
        parserHandler.getHandler(fileName));
  }

  @ParameterizedTest
  @ValueSource(strings = {"txt", "csv"})
  void testGetHandlerNotExistExtension(String fileName) {
    when(fileManager.getExtension(fileName))
        .thenReturn(fileName);
    assertThrows(NoHandlerException.class, () ->
        parserHandler.getHandler(fileName));
  }

  @ParameterizedTest
  @ValueSource(strings = {"json", "xml"})
  void testGetHandlerExistExtension(String fileName) {
    when(fileManager.getExtension(fileName))
        .thenReturn(fileName);
    assertDoesNotThrow(() ->
        parserHandler.getHandler(fileName));
  }
}
