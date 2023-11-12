package christmas.domain;

import static christmas.domain.CategoryMenu.getDessertCount;
import static christmas.domain.CategoryMenu.getMainCount;
import static christmas.domain.Event.CHRISTMAS_DAY;
import static christmas.domain.Event.GIFT;
import static christmas.domain.Event.SPECIAL_DAY;
import static christmas.domain.Event.WEEKDAY;
import static christmas.domain.Event.WEEKEND;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Discount {

    private static final List<Integer> specialDiscountDays = List.of(3, 10, 17, 24, 25, 31);
    private int discountAmount;
    private int totalPrice;

    public Discount(int totalPrice) {
        this.discountAmount = 0;
        this.totalPrice = totalPrice;
    }

    public Map<Event, Integer> storeBenefits(MyOrder myOrder, VisitDate visitDate, Discount discountInformation) {
        Map<Event, Integer> benefitStorage = new HashMap<>();
        if(totalPrice >= 10000) {
            discountInformation.checkChristmasDDayDiscount(benefitStorage, visitDate);
            discountInformation.checkWeekDayDiscount(benefitStorage, getDessertCount(myOrder.getMyOrders()), visitDate);
            discountInformation.checkWeekendDiscount(benefitStorage, getMainCount(myOrder.getMyOrders()), visitDate);
            discountInformation.checkSpecialDayDiscount(benefitStorage, visitDate);
            discountInformation.checkPresentDiscount(benefitStorage, discountInformation);
        }
        return benefitStorage;
    }

    private void checkChristmasDDayDiscount(Map<Event, Integer> benefitStorage, VisitDate visitDate) {
        if (visitDate.getDate() <= 25) {
            benefitStorage.put(CHRISTMAS_DAY, 1000 + 100 * (visitDate.getDate() - 1));
        }
    }

    private void checkWeekDayDiscount(Map<Event, Integer> benefitStorage, int dessertCount, VisitDate visitDate) {
        if (!visitDate.isWeekend()) {
            benefitStorage.put(WEEKDAY, 2023 * dessertCount);
        }
    }

    private void checkWeekendDiscount(Map<Event, Integer> benefitStorage, int mainCount, VisitDate visitDate) {
        if (visitDate.isWeekend()) {
            benefitStorage.put(WEEKEND, 2023 * mainCount);
        }
    }

    private void checkSpecialDayDiscount(Map<Event, Integer> benefitStorage, VisitDate visitDate) {
        if(specialDiscountDays.contains(visitDate.getDate())){
            benefitStorage.put(SPECIAL_DAY, 1000);
        }
    }

    private void checkPresentDiscount(Map<Event, Integer> benefitStorage, Discount discountInformation) {
        if(discountInformation.hasGift()){
            benefitStorage.put(GIFT, 25000);
        }
    }

    public void addDiscountAmount(Map<Event, Integer> benefitStorage) {
        for (int discountPrice : benefitStorage.values()) {
            discountAmount += discountPrice;
        }
    }

    public int getAmountAfterDiscount() {
        int amountAfterDiscount = totalPrice - discountAmount;
        if(hasGift()){
            amountAfterDiscount += 25000;
        }
        return amountAfterDiscount;
    }

    public boolean hasGift() {
        if(totalPrice >= 120000){
            return true;
        }
        return false;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
