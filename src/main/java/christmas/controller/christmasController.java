package christmas.controller;

import christmas.domain.Date;
import christmas.view.InputView;
import christmas.view.OutputView;

public class christmasController {

    public void run(){
        Date date = InputExpectedDate();
    }

    private Date InputExpectedDate() {
        while(true) {
            try {
                int expectedDate = InputView.InputVisitDate();
                return new Date(expectedDate);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
