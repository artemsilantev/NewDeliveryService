package com.artemsilantev.core.filemanager;

import com.artemsilantev.core.exception.AccessFileException;
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
      var errorMessage = String.format("Couldn't read from file with path \"%s\": %s", pathToFile,
          ioException.getMessage());
      throw new AccessFileException(errorMessage, ioException);
    }

  }

  public Stream<String> tryRead(String pathToFile) {
    var path = Paths.get(pathToFile);
    try {
      return Files.lines(path);
    } catch (IOException ioException) {
      return Stream.<String>builder().build();
    }
  }

  @Override
  public void write(String pathToFile, Collection<String> lines) {
    var path = Paths.get(pathToFile);
    try {
      createFileIfNotExists(path);
      Files.write(path, lines);
    } catch (IOException ioException) {
      var errorMessage = String.format("Couldn't write to file with path \"%s\": %s", pathToFile,
          ioException.getMessage());
      throw new AccessFileException(errorMessage, ioException);
    }
  }

  @Override
  public void createDirectories(String pathToDirectory) {
    var path = Paths.get(pathToDirectory);
    if (Files.notExists(path)) {
      try {
        Files.createDirectories(path);
      } catch (IOException ioException) {
        var errorMessage = String.format("Couldn't create directories by path \"%s\": %s ",
            pathToDirectory, ioException.getMessage());
        throw new AccessFileException(errorMessage, ioException);
      }
    }
  }

  @Override
  public void tryDelete(String pathToFile) {
    var path = Paths.get(pathToFile);
    try {
      Files.deleteIfExists(path);
    } catch (IOException ignored) {
    }
  }

  @Override
  public Stream<Path> getPaths(String pathToDirectory) {
    var path = Paths.get(pathToDirectory);
    try {
      return Files.list(path);
    } catch (IOException ioException) {
      var errorMessage = String.format("Couldn't get paths by directory path: \"%s\": %s ",
          pathToDirectory, ioException.getMessage());
      throw new AccessFileException(errorMessage, ioException);
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
      var errorMessage = String.format("Couldn't create file with path \"%s\": %s", path,
          ioException.getMessage());
      throw new AccessFileException(errorMessage, ioException);
    }
  }

}
