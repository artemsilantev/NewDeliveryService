package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoNecessarySymbolException extends Exception {
    private final Character expectedSymbol;
    private final Character currentSymbol;
    private final long index;


    @Override
    public String getMessage() {
        return String.format("Expected symbol: %c, received symbol: %c (index = %d) ", expectedSymbol, currentSymbol, index);
    }
}
