package christmas.domain;

import java.util.List;

public class VisitDate {

    private static final List<Integer> weekends = List.of(1,2,8,9,15,16,22,23,29,30);
    private final int date;
    private final boolean weekend;

    public VisitDate(int date) {
        validateDate(date);
        this.date = date;
        this.weekend = isWeekend(date);
    }

    private void validateDate(int date) {
        validateIsCorrectDateRange(date);
    }

    private void validateIsCorrectDateRange(int date) {
        if((date < 1) || (date > 31)){
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isWeekend(int date) {
        if(weekends.contains(date)){
            return true;
        }
        return false;
    }

    public boolean isWeekend() {
        if(weekend){
            return true;
        }
        return false;
    }

    public int getDate() {
        return date;
    }
}
