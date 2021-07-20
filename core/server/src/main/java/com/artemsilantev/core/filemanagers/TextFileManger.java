package com.artemsilantev.core.filemanagers;

import com.artemsilantev.core.exceptions.AccessFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

public class TextFileManger implements FileManager {

  @Override
  public Stream<String> read(String pathToFile) {
    var path = Paths.get(pathToFile);
    try {
      createFileIfNotExists(path);
      return Files.lines(path);
    } catch (IOException ioException) {
      throw new AccessFileException(
          String.format("Couldn't read from file with path \"%s\": %s", pathToFile,
              ioException.getMessage()));
    }

  }

  @Override
  public void write(String pathToFile, Collection<String> lines) {
    var path = Paths.get(pathToFile);
    try {
      createFileIfNotExists(path);
      Files.write(path, lines);
    } catch (IOException ioException) {
      throw new AccessFileException(
          String.format("Couldn't write to file with path \"%s\": %s", pathToFile,
              ioException.getMessage()));
    }
  }

  @Override
  public String getExtension(String pathToFile) {
    var extension = "";
    var index = pathToFile.lastIndexOf('.');
    if (index > 0) {
      extension = pathToFile.substring(index + 1);
    }
    return extension;
  }

  private void createFileIfNotExists(Path path) {
    try {
      if (Files.notExists(path)) {
        Files.createFile(path);
      }
    } catch (IOException ioException) {
      throw new AccessFileException(
          String.format("Couldn't create file with path \"%s\": %s", path,
              ioException.getMessage()));
    }
  }

}
