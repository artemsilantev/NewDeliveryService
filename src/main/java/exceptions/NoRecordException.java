package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoRecordException extends Exception {
    private final String entityType;
    private final Long id;


    @Override
    public String getMessage() {
        return String.format("%s was not found by this id (%d)",entityType,id);
    }
}
