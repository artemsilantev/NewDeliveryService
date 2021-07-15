package exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoadDataException extends RuntimeException{
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
