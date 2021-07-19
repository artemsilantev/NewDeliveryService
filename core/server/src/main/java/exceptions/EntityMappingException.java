package exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntityMappingException extends RuntimeException {

  private final String source;
  private final String target;
  private final String message;

  @Override
  public String getMessage() {
    return String.format("Couldn't map from %s to %s: %s", source, target, message);
  }
}
