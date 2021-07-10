package api.utils.readers;

import java.io.IOException;

public interface FileReader {
    Object readOrCreate(String pathToFile) throws IOException;
}
