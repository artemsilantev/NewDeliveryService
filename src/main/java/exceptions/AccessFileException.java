package exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccessFileException extends RuntimeException {
   private final String path;
   private final String problemMessage;



    @Override
    public String getMessage() {
        return String.format("File with path \"%s\" has problem: %s", path, problemMessage);
    }
}
