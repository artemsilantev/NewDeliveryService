package com.artemsilantev.core.filemanager;

import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;

public interface FileManager {

  Stream<String> read(String pathToFile);

  Stream<String> tryRead(String pathToFile);

  void write(String pathToFile, Collection<String> lines);

  void createDirectories(String pathToDirectory);

  void tryDelete(String pathToFile);

  Stream<Path> getPaths(String pathToDirectory);

  String getExtension(String pathToFile);
}
