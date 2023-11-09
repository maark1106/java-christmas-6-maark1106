package christmas.domain;

import java.util.List;
import java.util.Map;

public class Discount {

    private int discountAmount;
    private static final List<Integer> specialDiscountDays = List.of(3, 10, 17, 24, 25, 31);

    public Discount() {
        this.discountAmount = 0;
    }

    public boolean checkPresentationMenu(int totalPrice) {
        if (totalPrice >= 120000) {
            return true;
        }
        return false;
    }

    public void checkChristMasDDayDiscountDays(Map<String, Integer> benefitStorage, Date date) {
        if (specialDiscountDays.contains(date.getDate())) {
            benefitStorage.put("크리스마스 디데이 할인: ", 100 * date.getDate());
        }
    }

    public void checkWeekDayDiscount(Map<String, Integer> benefitStorage, int dessertCount, Date date) {
        if (!date.isWeekend()) {
            benefitStorage.put("평일 할인: ", 2023 * dessertCount);
        }
    }

    public void checkWeekendDiscount(Map<String, Integer> benefitStorage, int mainCount, Date date) {
        if (date.isWeekend()) {
            benefitStorage.put("주말 할인: ", 2023 * mainCount);
        }
    }
}
