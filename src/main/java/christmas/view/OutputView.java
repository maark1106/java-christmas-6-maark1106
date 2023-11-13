package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.Event;
import java.text.NumberFormat;
import java.util.Map;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String BENEFIT_PREVIEW_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_PREFIX_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_AMOUNT_BEFORE_DISCOUNT_PREFIX_MESSAGE = "<할인 전 총주문 금액>";
    private static final String PRESENTATION_MENU_PREFIX_MESSAGE = "<증정 메뉴>";
    private static final String BENEFIT_DETAILS_PREFIX_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_PREFIX_MESSAGE = "<총혜택 금액>";
    private static final String AMOUNT_AFTER_DISCOUNT_PREFIX_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String BADGE_PREFIX_MESSAGE = "<12월 이벤트 배지>";
    private static final String PRESENTATION_MENU = "샴페인 1개";
    private static final String MONEY_UNIT = "원";
    private static final String NOTHING_MESSAGE = "없음";

    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printBenefitPreviewMessage() {
        System.out.println(BENEFIT_PREVIEW_MESSAGE);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }

    public static void printOrderMenu(Map<String, Integer> myOrders) {
        System.out.println(ORDER_MENU_PREFIX_MESSAGE);
        for (String menuName : myOrders.keySet()) {
            System.out.println(menuName + " " + myOrders.get(menuName));
        }
    }

    public static void printTotalAmountBeforeDiscount(int orderAmount) {
        System.out.println(TOTAL_AMOUNT_BEFORE_DISCOUNT_PREFIX_MESSAGE);
        System.out.println(getNumberFormat(orderAmount));
    }

    public static void printPresentationMenu(Discount discountInformation) {
        System.out.println(PRESENTATION_MENU_PREFIX_MESSAGE);
        if (!discountInformation.hasGift()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        System.out.println(PRESENTATION_MENU);
    }

    public static void printBenefitDetails(Map<Event, Integer> benefitStorage) {
        System.out.println(BENEFIT_DETAILS_PREFIX_MESSAGE);
        if (benefitStorage.isEmpty()) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        printEachBenefit(benefitStorage);
    }

    private static void printEachBenefit(Map<Event, Integer> benefitStorage) {
        for (Event event : benefitStorage.keySet()) {
            String numberFormat = getNumberFormat(benefitStorage.get(event) * -1);
            System.out.println(event.getEventName() + ": " + numberFormat);
        }
    }

    public static void printTotalBenefitAmount(int discountAmount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_PREFIX_MESSAGE);
        if (discountAmount == 0) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        System.out.println(getNumberFormat(discountAmount * -1));
    }

    public static void printAmountAfterDiscount(int amountAfterDiscount) {
        System.out.println(AMOUNT_AFTER_DISCOUNT_PREFIX_MESSAGE);
        System.out.println(getNumberFormat(amountAfterDiscount));
    }

    public static void printBadge(Badge eventBadge) {
        System.out.println(BADGE_PREFIX_MESSAGE);
        if (eventBadge == null) {
            System.out.println(NOTHING_MESSAGE);
            return;
        }
        System.out.println(eventBadge);
    }

    private static String getNumberFormat(int number) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        return numberFormat.format(number) + MONEY_UNIT;
    }
}
