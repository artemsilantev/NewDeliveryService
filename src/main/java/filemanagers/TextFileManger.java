package filemanagers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;


public class TextFileManger implements FileManager {
    @Override
    public Collection<String> read(String pathToFile) throws IOException {
        var path = Paths.get(pathToFile);
        if(Files.notExists(path)){
            Files.createFile(path);
        }
        return Files.readAllLines(path);
    }

    @Override
    public void write(String pathToFile, Collection<String> lines) throws IOException {
        var path = Paths.get(pathToFile);
        Files.write(path, lines);
    }

}
