package filemanagers;

import java.io.IOException;
import java.util.Collection;

public interface FileManager {
    Collection<String> read(String pathToFile) throws IOException;
    void write(String path, Collection<String> lines) throws IOException;
}
