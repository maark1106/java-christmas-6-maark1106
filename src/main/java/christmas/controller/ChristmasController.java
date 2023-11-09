package christmas.controller;

import christmas.domain.CategoryMenu;
import christmas.domain.Date;
import christmas.domain.MyOrder;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private static final ChristmasService christmasService = new ChristmasService();

    public void run(){
        Date date = inputExpectedDate();
        MyOrder myOrder = orderMenus();
        printOrderInformation(myOrder, date);
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
                christmasService.validateMyOrder(myOrder);
                return myOrder;
            }catch (IllegalArgumentException e){
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void printOrderInformation(MyOrder myOrder, Date date) {
        OutputView.printOrderMenu(myOrder);
    }
}
