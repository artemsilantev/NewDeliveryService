package com.artemsilantev.core.test.filemanager;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.artemsilantev.core.exception.AccessFileException;
import com.artemsilantev.core.filemanager.TextFileManger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


class TextFileManagerTest {

  @TempDir
  static Path sharedTempDir;

  TextFileManger fileManager = new TextFileManger();

  @ParameterizedTest
  @NullAndEmptySource
  void testReadNullAndEmptyPath(String path) {
    assertThrows(AccessFileException.class, () ->
        fileManager.read(path));
  }

  @Test
  void testReadNotExistFile() {
    var path = sharedTempDir.resolve("testReadNotExist.txt");
    Stream<String> lines = fileManager.read(path.toAbsolutePath().toString());
    assertAll(
        () -> assertTrue(Files.exists(path)),
        () -> assertTrue(lines.findAny().isEmpty())
    );
  }

  @Test
  void testReadFromFile() throws IOException {
    Path path = sharedTempDir.resolve("testWriteReadLines.txt");
    var lines = List.of("test text");
    var expected = "test text";
    Files.createFile(path);
    Files.write(path, lines);
    Stream<String> actual = fileManager.read(path.toString());
    assertEquals(expected, actual.collect(Collectors.joining("")));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void testTryReadNullAndEmptyPath(String path) {
    Stream<String> actual = fileManager.tryRead(path);
    assertTrue(actual.findAny().isEmpty());
  }

  @Test
  void testTryReadFromFile() throws IOException {
    Path path = sharedTempDir.resolve("testWriteTryReadLines.txt");
    var lines = List.of("test text");
    var expected = "test text";
    Files.createFile(path);
    Files.write(path, lines);
    Stream<String> actual = fileManager.tryRead(path.toString());
    assertEquals(expected, actual.collect(Collectors.joining("")));
  }


  @ParameterizedTest
  @NullAndEmptySource
  void testWriteNullAndEmptyPath(String path) {
    var lines = List.of("");
    assertThrows(AccessFileException.class, () ->
        fileManager.write(path, lines));
  }

  @Test
  void testWriteNullLines() {
    var path = sharedTempDir.resolve("testWriteNotExist.txt").toString();
    assertThrows(AccessFileException.class, () ->
        fileManager.write(path, null));
  }

  @Test
  void testWriteNotExistFile() {
    var path = sharedTempDir.resolve("testWriteNotExistFile.txt");
    var lines = List.of("");
    fileManager.write(path.toAbsolutePath().toString(), lines);
    assertTrue(Files.exists(path));
  }

  @Test
  void testCreateDirectoriesNullPath() {
    assertThrows(AccessFileException.class, () ->
        fileManager.createDirectories(null));
  }

  @Test
  void testCreateDirectoriesNotExist() {
    var path = sharedTempDir.resolve("createDirectories/notExist");
    fileManager.createDirectories(path.toAbsolutePath().toString());
    assertTrue(Files.exists(path));
  }

  @Test
  void testCreateDirectoriesExist() {
    var path = sharedTempDir.resolve(sharedTempDir);
    fileManager.createDirectories(path.toAbsolutePath().toString());
    assertTrue(Files.exists(path));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void testTryDeleteNullAndEmptyPath(String path) {
    assertDoesNotThrow(() ->
        fileManager.tryDelete(path));
  }

  @Test
  void testTryDeleteExistFile() throws IOException {
    var path = sharedTempDir.resolve("test.txt");
    Files.createFile(path);
    fileManager.tryDelete(path.toAbsolutePath().toString());
    assertTrue(Files.notExists(path));
  }

  @Test
  void testGetPathsNullPath() {
    assertThrows(AccessFileException.class, () ->
        fileManager.getPaths(null));
  }

  @Test
  void testGetPathsNotExist() {
    assertThrows(AccessFileException.class, () ->
        fileManager.getPaths("1"));
  }

  @Test
  void testGetPathsExist() throws IOException {
    var paths = List.of(sharedTempDir.resolve("getPathsExist1.txt"),
        sharedTempDir.resolve("getPathsExist2.txt"));
    Files.createFile(paths.get(0));
    Files.createFile(paths.get(1));
    var actual = fileManager.getPaths(sharedTempDir.toAbsolutePath().toString())
        .collect(Collectors.toList());
    assertTrue(actual.containsAll(paths));
  }

  @Test
  void testGetExtensionNotValid() {
    var expected = "";
    String actual = fileManager.getExtension("");
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @ValueSource(strings = {"b.txt", "b.json.txt"})
  void testGetExtensionOneDot(String pathToFile) {
    var expected = "txt";
    String actual = fileManager.getExtension(pathToFile);
    assertEquals(expected, actual);
  }
}
