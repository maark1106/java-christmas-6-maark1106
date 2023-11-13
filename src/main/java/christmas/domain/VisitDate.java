package christmas.domain;

import java.util.List;

public class VisitDate {

    private static final String INVALID_DATE_EXCEPTION_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int EVENT_START_DATE = 1;
    private static final int EVENT_END_DATE = 31;
    private static final List<Integer> weekends = List.of(1,2,8,9,15,16,22,23,29,30);

    private final int date;
    private final boolean weekend;

    public VisitDate(int date) {
        validateDate(date);
        this.date = date;
        this.weekend = weekends.contains(date);
    }

    private void validateDate(int date) {
        validateIsCorrectDateRange(date);
    }

    private void validateIsCorrectDateRange(int date) {
        if((date < EVENT_START_DATE) || (date > EVENT_END_DATE)){
            throw new IllegalArgumentException(INVALID_DATE_EXCEPTION_MESSAGE);
        }
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
