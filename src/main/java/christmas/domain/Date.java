package christmas.domain;

public class Date {
    
    private final int date;

    public Date(int date) {
        validateDate(date);
        this.date = date;
    }

    private void validateDate(int date) {
        validateIsCorrectDateRange(date);
    }

    private void validateIsCorrectDateRange(int date) {
        if((date < 1) || (date > 31)){
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
