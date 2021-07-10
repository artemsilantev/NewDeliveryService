package exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoEnoughSymbols extends Exception{
    private final Character expectedSymbol;
    private final Integer counter;

    @Override
    public String getMessage() {
        return String.format("Expected symbol: %c, need: %d symbols", expectedSymbol, counter);
    }
}
