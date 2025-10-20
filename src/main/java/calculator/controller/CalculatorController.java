package calculator.controller;
import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final CalculatorService calculatorService = new CalculatorService();

    public void run() {
        try {
            String input = inputView.readInput();
            int result = calculatorService.defaultDelimiters(input);
            outputView.showResult(String.valueOf(result));
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}


