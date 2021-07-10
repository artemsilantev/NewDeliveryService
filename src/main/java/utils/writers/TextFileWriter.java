package utils.writers;

import api.utils.writers.FileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileWriter implements FileWriter {
    @Override
    public void reWriteTextToFile(String pathToFile, String text) throws IOException {
        Path path = Paths.get(pathToFile);
        Files.deleteIfExists(path);
        Files.createFile(path);
        Files.writeString(path,text);
    }
}
