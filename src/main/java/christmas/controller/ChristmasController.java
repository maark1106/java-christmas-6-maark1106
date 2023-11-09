package christmas.controller;

import static christmas.domain.CategoryMenu.*;

import christmas.domain.CategoryMenu;
import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.MyOrder;
import christmas.service.ChristmasService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public class ChristmasController {

    private static final ChristmasService christmasService = new ChristmasService();

    public void run(){
        Date date = inputExpectedDate();
        MyOrder myOrder = orderMenus();
        int totalPrice = getOrderInformation(myOrder);
        getBenefitInformation(myOrder, date, totalPrice);
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

    private int getOrderInformation(MyOrder myOrder) {
        OutputView.printOrderMenu(myOrder);
        int orderAmount = christmasService.getOrderAmount(myOrder);
        OutputView.printTotalAmountBeforeDiscount(orderAmount);
        return orderAmount;
    }

    private void getBenefitInformation(MyOrder myOrder,Date date, int totalPrice) {
        Discount discountInformation = new Discount();
        boolean presentationCheck = discountInformation.checkPresentationMenu(totalPrice);
        OutputView.printPresentationMenu(presentationCheck);

        Map<String, Integer> benefitStorage = new HashMap<>();
        if(totalPrice >= 10000) {
            discountInformation.checkSpecialDiscountDays(benefitStorage, date);
            discountInformation.checkWeekDayDiscount(benefitStorage, getDessertCount(myOrder.getMyOrders()), date);
            discountInformation.checkWeekendDiscount(benefitStorage, CategoryMenu.getMainCount(myOrder.getMyOrders()), date);
        }
    }
}
