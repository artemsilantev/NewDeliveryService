package validators.text;

import org.apache.commons.lang3.StringUtils;
import validators.Validator;

import java.util.*;

public class JsonTextValidator implements Validator<String> {
    @Override
    public Collection<String> validate(String line) {
       Collection<String> problems = new ArrayList<>();
        if (StringUtils.isBlank(line))
            problems.add("Line is blank");

        problems.addAll(checkStartEndSymbols(line));
        problems.addAll(checkQuotes(line));
        return problems;
    }

    private Collection<String> checkStartEndSymbols(String line) {
        var problems = new ArrayList<String>();
        var charactersLIFO = Collections.asLifoQueue(new ArrayDeque<Character>());
        var symbolsStart = "[{";
        var symbolsEnd = "]}";
        for (var i = 0; i < line.length(); i++) {
            var symbol = line.charAt(i);
            if (symbolsStart.indexOf(symbol) >= 0) {
                charactersLIFO.add(symbol);
            } else if (symbolsEnd.indexOf(symbol) >= 0) {
                var index = symbolsEnd.indexOf(symbol);
                var symbolPoll = charactersLIFO.poll();
                if (symbolPoll == null || symbolsStart.charAt(index) != symbolPoll) {
                    problems.add(String.format("Expected symbol: '%c' received symbol: '%c' (index = %d)",
                                    symbolsStart.charAt(index), symbol, index));
                }
            }
        }
        var i = line.length();
        while (!charactersLIFO.isEmpty()) {
            var symbol = charactersLIFO.poll();
            var index = symbolsStart.indexOf(symbol);
            problems.add(String.format("Expected symbol: '%c' received symbol: %c (index = %d)",
                    symbolsEnd.charAt(index), ' ', i++));
        }
        return problems;
    }

    private Collection<String> checkQuotes(String line) {
        var problems = new ArrayList<String>();
        var quotesCounter = 0;
        for (var symbol : line.toCharArray()) {
            if (symbol == '\"') quotesCounter++;
        }
        var quotesNeed = quotesCounter % 2;
        if (quotesNeed != 0) {
            problems.add(String.format("Expected symbol: %c, need: %d symbols", '\"', quotesNeed));
        }
        return problems;
    }
}

