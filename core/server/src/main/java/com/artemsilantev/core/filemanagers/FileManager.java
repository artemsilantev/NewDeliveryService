package com.artemsilantev.core.filemanagers;

import java.util.Collection;
import java.util.stream.Stream;

public interface FileManager {

  Stream<String> read(String pathToFile);

  void write(String pathToFile, Collection<String> lines);

  String getExtension(String pathToFile);
}
