package christmas.controller;

import static christmas.domain.CategoryMenu.*;

import christmas.domain.Badge;
import christmas.domain.CategoryMenu;
import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.MenuPrice;
import christmas.domain.MyOrder;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public class ChristmasController {

    public void run(){
        OutputView.printWelcomeMessage();
        Date date = inputExpectedDate();
        MyOrder myOrder = orderMenus();
        int totalPrice = getOrderInformation(myOrder);
        Discount benefitInformation = getBenefitInformation(myOrder, date, totalPrice);
        getResult(benefitInformation, totalPrice);
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
                CategoryMenu.validateMyOrder(myOrder);
                return myOrder;
            }catch (IllegalArgumentException e){
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int getOrderInformation(MyOrder myOrder) {
        OutputView.printBenefitPreviewMessage();
        OutputView.printOrderMenu(myOrder.getMyOrders());
        int orderAmount = MenuPrice.getOrderAmount(myOrder);
        OutputView.printTotalAmountBeforeDiscount(orderAmount);
        return orderAmount;
    }

    private Discount getBenefitInformation(MyOrder myOrder, Date date, int totalPrice) {
        Discount discountInformation = new Discount();
        boolean presentationCheck = discountInformation.checkPresentationMenu(totalPrice);
        OutputView.printPresentationMenu(presentationCheck);

        Map<String, Integer> benefitStorage = new HashMap<>();
        if(totalPrice >= 10000) {
            discountInformation.checkChristmasDDayDiscountDays(benefitStorage, date);
            discountInformation.checkWeekDayDiscount(benefitStorage, getDessertCount(myOrder.getMyOrders()), date);
            discountInformation.checkWeekendDiscount(benefitStorage, CategoryMenu.getMainCount(myOrder.getMyOrders()), date);
            discountInformation.checkSpecialDayDiscount(benefitStorage,date);
            discountInformation.checkPresentDiscount(benefitStorage,presentationCheck);
        }
        OutputView.printBenefitDetails(benefitStorage);
        discountInformation.addDiscountAmount(benefitStorage);
        return discountInformation;
    }

    private void getResult(Discount benefitInformation, int totalPrice) {
        OutputView.printTotalBenefitAmount(benefitInformation.getDiscountAmount());
        OutputView.printAmountAfterDiscount(benefitInformation,totalPrice);
        Badge eventBadge = Badge.getEventBadge(benefitInformation);
        OutputView.printBadge(eventBadge);
    }
}
