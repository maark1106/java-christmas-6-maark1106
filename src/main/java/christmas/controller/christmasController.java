package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class christmasController {

    public void run(){
        InputExpectedDate();
    }

    private void InputExpectedDate() {
        while(true) {
            try {
                int expectedDate = InputView.InputVisitDate();
                return new Day(expectedDate);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
