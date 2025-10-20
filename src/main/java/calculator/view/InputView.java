package calculator.view;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readInput(){
        System.out.println("계산식을 입력해주세요.");
        return Console.readLine();
    }
    
}
