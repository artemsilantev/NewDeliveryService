package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.BaseEntity;

@Getter
@AllArgsConstructor
public class IllegalEntityException extends Exception {
    private final BaseEntity entity;
    private final String message;

    @Override
    public String getMessage() {
        return String.format("%s with id(%d) have problem: %s", entity.getClass().getSimpleName(), entity.getId(), message);
    }
}
