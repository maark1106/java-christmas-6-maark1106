package christmas.util;

public class InputValidator {

    public static void validateVisitDate(String visitDate){
        visitDateEmptyException(visitDate);
        inputNotIntegerTypeException(visitDate);
    }

    private static void visitDateEmptyException(String visitDate) {
        if(visitDate.isEmpty()){
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void inputNotIntegerTypeException(String visitDate) {
        try {
            Integer.parseInt(visitDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateOrderMenus(String orderMenus) {
        orderMenusEmptyException(orderMenus);
        inputContainEmptyException(orderMenus);
    }

    private static void orderMenusEmptyException(String orderMenus) {
        if(orderMenus.isEmpty()){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void inputContainEmptyException(String orderMenus) {
        if(orderMenus.contains(" ")){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
