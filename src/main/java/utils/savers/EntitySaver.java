package utils.savers;

import api.utils.parsers.Parser;
import api.utils.savers.Saver;
import api.utils.writers.FileWriter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;

@AllArgsConstructor
public class EntitySaver implements Saver {

    private final Parser parser;
    private final FileWriter writer;


    @Override
    public <E> void save(E entityToSave, String path) {
        try {
            String textToWrite = parser.serializeEntity(entityToSave);
            writer.reWriteTextToFile(path, textToWrite);
            System.out.println("Recreate file: " + path);
        } catch (IOException ioException) {
            System.out.println("Failed write to file: " + ioException.getMessage());
        } catch (Exception exception) {
            System.out.println("Problem with saving data: " + exception.getMessage());
        }
    }
}
