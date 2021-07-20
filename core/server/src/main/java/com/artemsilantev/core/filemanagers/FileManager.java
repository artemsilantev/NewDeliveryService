package com.artemsilantev.core.filemanagers;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Stream;

public interface FileManager {

  Stream<String> read(String pathToFile) throws IOException;

  void write(String path, Collection<String> lines) throws IOException;

  String getExtension(String path);
}
