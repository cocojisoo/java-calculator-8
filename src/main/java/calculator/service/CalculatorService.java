package calculator.service;
import java.util.regex.Pattern;

public class CalculatorService {
    public int defaultDelimiters(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }
        if (input.startsWith("//")) {
            return customDelimiter(input);
        }

        String[] tokens = input.split(",|:");
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

    public int customDelimiter(String input) {
        String normalized = input.replace("\\n", "\n");
        int newlineIndex = normalized.indexOf('\n');
        if (newlineIndex == -1) {
            throw new IllegalArgumentException();
        }
        String custom = normalized.substring(2, newlineIndex);
        if (custom.isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        int numbersStart = newlineIndex + 1;
        String numbers = normalized.substring(numbersStart);
        String[] tokens = numbers.split(Pattern.quote(custom) + "|,|:");
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
