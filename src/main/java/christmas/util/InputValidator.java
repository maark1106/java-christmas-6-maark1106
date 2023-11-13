package christmas.util;

public class InputValidator {

    private static final String INVALID_DATE_EXCEPTION_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER_EXCEPTION_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String EMPTY_VALUE = " ";

    public static void validateVisitDate(String visitDate){
        visitDateEmptyException(visitDate);
        inputNotIntegerTypeException(visitDate);
    }

    private static void visitDateEmptyException(String visitDate) {
        if(visitDate.isEmpty()){
            throw new IllegalArgumentException(INVALID_DATE_EXCEPTION_MESSAGE);
        }
    }

    private static void inputNotIntegerTypeException(String visitDate) {
        try {
            Integer.parseInt(visitDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_DATE_EXCEPTION_MESSAGE);
        }
    }

    public static void validateOrderMenus(String orderMenus) {
        orderMenusEmptyException(orderMenus);
        inputContainEmptyException(orderMenus);
        orderMenusUnpairedException(orderMenus);
    }

    private static void orderMenusUnpairedException(String orderMenus) {
        if(countDash(orderMenus) -1 != countComma(orderMenus)){
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private static int countDash(String orderMenus) {
        return (int) orderMenus.chars()
                .filter(c -> c == '-')
                .count();
    }

    private static int countComma(String orderMenus) {
        return (int) orderMenus.chars()
                .filter(c -> c == ',')
                .count();
    }

    private static void orderMenusEmptyException(String orderMenus) {
        if(orderMenus.isEmpty()){
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private static void inputContainEmptyException(String orderMenus) {
        if(orderMenus.contains(EMPTY_VALUE)){
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }
}
