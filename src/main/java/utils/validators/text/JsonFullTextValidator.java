package utils.validators.text;

import api.utils.validators.TextValidator;
import exceptions.EmptyStringException;
import exceptions.NoEnoughSymbols;
import exceptions.NoNecessarySymbolException;

import java.util.*;

public class JsonFullTextValidator implements TextValidator {
    @Override
    public Collection<Exception> validate(Object textToValidate) {

        Collection<Exception> exceptions = new ArrayList<>();

        if (!(textToValidate instanceof String)) {
            exceptions.add(new ClassCastException("Text should be string"));
            return exceptions;
        }

        String text = (String) textToValidate;
        if ((text.isEmpty()))
            exceptions.add(new EmptyStringException());

        checkStartEndSymbols(text, exceptions);
        checkQuotes(text, exceptions);
        return exceptions;
    }

    private void checkStartEndSymbols(String text, Collection<Exception> exceptions) {
        Queue<Character> charactersLIFO = Collections.asLifoQueue(new ArrayDeque<>());
        String symbolsStart = "[{";
        String symbolsEnd = "]}";
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if (symbolsStart.indexOf(symbol) >= 0) {
                charactersLIFO.add(symbol);
            } else if (symbolsEnd.indexOf(symbol) >= 0) {
                int index = symbolsEnd.indexOf(symbol);
                Character symbolPoll = charactersLIFO.poll();
                if (symbolPoll == null || symbolsStart.charAt(index) != symbolPoll) {
                    exceptions.add(new NoNecessarySymbolException(symbolsStart.charAt(index), symbol, index));
                }
            }
        }
        int i = text.length();
        while (!charactersLIFO.isEmpty()){
            Character symbol = charactersLIFO.poll();
            int index = symbolsStart.indexOf(symbol);
            exceptions.add(new NoNecessarySymbolException(symbolsEnd.charAt(index), ' ', i++));

        }
    }

    private void checkQuotes(String text, Collection<Exception> exceptions) {
        int quotesCounter = 0;
        for (char symbol : text.toCharArray()) {
            if (symbol == '\"') quotesCounter++;
        }
        int quotesNeed = quotesCounter % 2;
        if (quotesNeed != 0) {
            exceptions.add(new NoEnoughSymbols('\"', quotesNeed));
        }
    }
}

