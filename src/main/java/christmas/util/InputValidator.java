package christmas.util;

public class InputValidator {

    public static void validateVisitDate(String visitDate){
        inputEmptyException(visitDate);
    }

    private static void inputEmptyException(String visitDate) {
        if(visitDate.isEmpty()){
            throw new IllegalArgumentException("공백일 수 없습니다");
        }
    }
}
