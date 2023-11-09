package christmas.controller;

import christmas.domain.Date;
import christmas.domain.MyOrder;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    public void run(){
        Date date = inputExpectedDate();
        MyOrder myOrder = orderMenus();
    }

    private Date inputExpectedDate() {
        while(true) {
            try {
                int expectedDate = InputView.InputVisitDate();
                return new Date(expectedDate);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private MyOrder orderMenus() {
        while(true){
            try{
                String orderMenus = InputView.InputOrderMenus();
                MyOrder myOrder = new MyOrder(orderMenus);
                return myOrder;
            }catch (IllegalArgumentException e){
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

}
