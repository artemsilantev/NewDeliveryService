package utils.readers;

import api.utils.readers.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileReader implements FileReader {

    @Override
    public String readOrCreate(String pathToFile) throws IOException{
        Path path = Paths.get(pathToFile);
            if(Files.notExists(path))
                Files.createFile(path);
            return Files.readString(path);
    }
}
