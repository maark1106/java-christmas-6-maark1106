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
        if (date.getDate() <= 25) {
            benefitStorage.put("크리스마스 디데이 할인: ", 1000 + 100 * (date.getDate() - 1));
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

    public void checkSpecialDayDiscount(Map<String, Integer> benefitStorage, Date date) {
        if(specialDiscountDays.contains(date.getDate())){
            benefitStorage.put("특별 할인: ", 1000);
        }
    }

    public void checkPresentDiscount(Map<String, Integer> benefitStorage, boolean presentationCheck) {
        if(presentationCheck){
            benefitStorage.put("증정 이벤트: ", 25000);
        }
    }

    public void addDiscountAmount(Map<String, Integer> benefitStorage) {
        for (int discountPrice : benefitStorage.values()) {
            discountAmount += discountPrice;
        }
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
