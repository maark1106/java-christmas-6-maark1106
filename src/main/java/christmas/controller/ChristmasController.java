package christmas.controller;

import static christmas.domain.Badge.getEventBadge;
import static christmas.domain.CategoryMenu.validateMyOrder;

import christmas.domain.Discount;
import christmas.domain.Event;
import christmas.domain.MenuPrice;
import christmas.domain.MyOrder;
import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasController {

    private VisitDate visitDate;
    private MyOrder myOrder;

    public void run() {
        visitDate = startReservation();
        myOrder = orderMenus();
        int totalPrice = getOrderInformation(myOrder);
        Discount discountInformation = checkBenefits(totalPrice);
        getResult(discountInformation);
    }

    private VisitDate startReservation() {
        OutputView.printWelcomeMessage();
        while (true) {
            try {
                int expectedDate = InputView.inputVisitDate();
                return new VisitDate(expectedDate);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private MyOrder orderMenus() {
        while (true) {
            try {
                String orderMenus = InputView.inputOrderMenus();
                MyOrder myOrder = new MyOrder(orderMenus);
                validateMyOrder(myOrder);
                return myOrder;
            } catch (IllegalArgumentException e) {
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

    private Discount checkBenefits(int totalPrice) {
        Discount discountInformation = new Discount(totalPrice);
        OutputView.printPresentationMenu(discountInformation);

        Map<Event, Integer> benefitStorage = discountInformation.storeBenefits(myOrder, visitDate, discountInformation);
        OutputView.printBenefitDetails(benefitStorage);
        discountInformation.addDiscountAmount(benefitStorage);
        return discountInformation;
    }

    private void getResult(Discount discountInformation) {
        OutputView.printTotalBenefitAmount(discountInformation.getDiscountAmount());
        OutputView.printAmountAfterDiscount(discountInformation.getAmountAfterDiscount());
        OutputView.printBadge(getEventBadge(discountInformation.getDiscountAmount()));
    }
}
