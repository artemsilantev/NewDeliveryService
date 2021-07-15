package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoRecordException extends RuntimeException{
    private final String entityType;
    private final Long id;


    @Override
    public String getMessage() {
        return String.format("Object in this storage %s was not found by this id (%d)",entityType,id);
    }
}
