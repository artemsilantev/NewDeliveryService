package api.utils.writers;

import java.io.IOException;
import java.util.Collection;

public interface FileWriter {
    void reWriteTextToFile(String path, String text) throws IOException;
}
