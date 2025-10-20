package calculator.service;
import java.util.regex.Pattern;

public class CalculatorService {
    public int defaultDelimiters(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        String[] tokens = input.split(",|:");
        return sumTokens(tokens);
    }

    public int customDelimiter(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }
        if (!input.startsWith("//")) {
            return defaultDelimiters(input);
        }

        int newlineIndex = input.indexOf('\n');
        if (newlineIndex == -1) {
            throw new IllegalArgumentException();
        }

        String custom = input.substring(2, newlineIndex);
        if (custom.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String numbers = input.substring(newlineIndex + 1);
        String[] tokens = numbers.split(Pattern.quote(custom));
        return sumTokens(tokens);
    }

    private int sumTokens(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            if (token == null) {
                throw new IllegalArgumentException();
            }
            String trimmed = token.trim();
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException();
            }
            int value = parsePositiveOrZero(trimmed);
            if (value < 0) {
                throw new IllegalArgumentException();
            }
            sum += value;
        }
        return sum;
    }

    private int parsePositiveOrZero(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
