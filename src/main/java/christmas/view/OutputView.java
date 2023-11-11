package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.MyOrder;
import java.text.NumberFormat;
import java.util.Map;

public class OutputView {

    public static void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printBenefitPreviewMessage() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public static void printOrderMenu(MyOrder myOrder) {
        Map<String, Integer> myOrders = myOrder.getMyOrders();

        System.out.println("<주문 메뉴>");
        for (String menuName : myOrders.keySet()) {
            System.out.println(menuName + " " + myOrders.get(menuName));
        }
    }

    public static void printTotalAmountBeforeDiscount(int orderAmount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(getNumberFormat(orderAmount));
    }

    public static void printPresentationMenu(boolean presentationCheck) {
        System.out.println("<증정 메뉴>");
        if (!presentationCheck) {
            System.out.println("없음");
            return;
        }
        System.out.println("샴페인 1개");
    }

    public static void printBenefitDetails(Map<String, Integer> benefitStorage) {
        System.out.println("<혜택 내역>");
        if (benefitStorage.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (String benefitName : benefitStorage.keySet()) {
            String numberFormat = getNumberFormat(benefitStorage.get(benefitName) * -1);
            System.out.println(benefitName + ": " + numberFormat);
        }
    }

    public static void printTotalBenefitAmount(Discount benefitInformation) {
        System.out.println("<총혜택 금액>");
        int discountAmount = benefitInformation.getDiscountAmount();
        if (discountAmount == 0) {
            System.out.println("없음");
            return;
        }
        System.out.println(getNumberFormat(discountAmount * -1));
    }

    public static void printAmountAfterDiscount(Discount benefitInformation, int totalPrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        int amountAfterDiscount = totalPrice - benefitInformation.getDiscountAmount();
        if(benefitInformation.hasGift()){
            amountAfterDiscount += 25000;
        }
        System.out.println(getNumberFormat(amountAfterDiscount));
    }

    public static void printBadge(Badge eventBadge) {
        System.out.println("<12월 이벤트 배지>");
        if(eventBadge == null){
            System.out.println("없음");
            return;
        }
        System.out.println(eventBadge);
    }

    private static String getNumberFormat(int number) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(number) + "원";
    }
}
