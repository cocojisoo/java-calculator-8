package calculator.service;

public class CalculatorService {
    public int defaultDelimiters(String input) {
        if (input == null || input.isBlank()) {
            return 0;
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

    private int parsePositiveOrZero(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
