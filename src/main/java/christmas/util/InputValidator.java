package christmas.util;

public class InputValidator {

    public static void validateVisitDate(String visitDate){
        inputEmptyException(visitDate);
        inputNotIntegerTypeException(visitDate);
    }

    private static void inputNotIntegerTypeException(String visitDate) {
        try {
            Integer.parseInt(visitDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("입력값이 정수가 아닙니다.");
        }
    }

    private static void inputEmptyException(String visitDate) {
        if(visitDate.isEmpty()){
            throw new IllegalArgumentException("공백일 수 없습니다");
        }
    }

    public static void validateOrderMenus(String orderMenus) {
        inputEmptyException(orderMenus);
        inputContainEmptyException(orderMenus);
    }

    private static void inputContainEmptyException(String orderMenus) {
        if(orderMenus.contains(" ")){
            throw new IllegalArgumentException("입력 형식에 맞지 않습니다");
        }
    }
}
