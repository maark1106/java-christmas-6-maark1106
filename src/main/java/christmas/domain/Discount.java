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

    private static final int MINIMUM_DISCOUNT_AMOUNT_VALUE = 10000;
    private static final int LAST_DAY_OF_CHRISTMAS_D_DAY_DISCOUNT = 25;
    private static final int CHRISTMAS_D_DAY_BASIC_DISCOUNT_VALUE = 1000;
    private static final int CHRISTMAS_D_DAY_DISCOUNT_PER_DAY_VALUE = 100;
    private static final int WEEKDAY_DISCOUNT_VALUE = 2023;
    private static final int WEEKEND_DISCOUNT_VALUE = 2023;
    private static final int SPECIAL_DAY_DISCOUNT_VALUE = 1000;
    private static final int GIFT_DISCOUNT_VALUE = 25000;
    private static final int MINIMUM_GIFT_DISCOUNT_VALUE = 120000;
    private static final int ZERO_VALUE = 0;
    private static final List<Integer> specialDiscountDays = List.of(3, 10, 17, 24, 25, 31);

    private int discountAmount;
    private int totalPrice;

    public Discount(int totalPrice) {
        this.discountAmount = ZERO_VALUE;
        this.totalPrice = totalPrice;
    }

    public Map<Event, Integer> storeBenefits(MyOrder myOrder, VisitDate visitDate, Discount discountInformation) {
        Map<Event, Integer> benefitStorage = new HashMap<>();
        if (totalPrice >= MINIMUM_DISCOUNT_AMOUNT_VALUE) {
            discountInformation.checkChristmasDDayDiscount(benefitStorage, visitDate);
            discountInformation.checkWeekDayDiscount(benefitStorage, getDessertCount(myOrder.getMyOrders()), visitDate);
            discountInformation.checkWeekendDiscount(benefitStorage, getMainCount(myOrder.getMyOrders()), visitDate);
            discountInformation.checkSpecialDayDiscount(benefitStorage, visitDate);
            discountInformation.checkPresentDiscount(benefitStorage, discountInformation);
        }
        return benefitStorage;
    }

    private void checkChristmasDDayDiscount(Map<Event, Integer> benefitStorage, VisitDate visitDate) {
        if (visitDate.getDate() <= LAST_DAY_OF_CHRISTMAS_D_DAY_DISCOUNT) {
            benefitStorage.put(CHRISTMAS_DAY,
                    CHRISTMAS_D_DAY_BASIC_DISCOUNT_VALUE + CHRISTMAS_D_DAY_DISCOUNT_PER_DAY_VALUE * (visitDate.getDate()
                            - 1));
        }
    }

    private void checkWeekDayDiscount(Map<Event, Integer> benefitStorage, int dessertCount, VisitDate visitDate) {
        if (!visitDate.isWeekend() && dessertCount > ZERO_VALUE) {
            benefitStorage.put(WEEKDAY, WEEKDAY_DISCOUNT_VALUE * dessertCount);
        }
    }

    private void checkWeekendDiscount(Map<Event, Integer> benefitStorage, int mainCount, VisitDate visitDate) {
        if (visitDate.isWeekend() && mainCount > ZERO_VALUE) {
            benefitStorage.put(WEEKEND, WEEKEND_DISCOUNT_VALUE * mainCount);
        }
    }

    private void checkSpecialDayDiscount(Map<Event, Integer> benefitStorage, VisitDate visitDate) {
        if (specialDiscountDays.contains(visitDate.getDate())) {
            benefitStorage.put(SPECIAL_DAY, SPECIAL_DAY_DISCOUNT_VALUE);
        }
    }

    private void checkPresentDiscount(Map<Event, Integer> benefitStorage, Discount discountInformation) {
        if (discountInformation.hasGift()) {
            benefitStorage.put(GIFT, GIFT_DISCOUNT_VALUE);
        }
    }

    public void addDiscountAmount(Map<Event, Integer> benefitStorage) {
        for (int discountPrice : benefitStorage.values()) {
            discountAmount += discountPrice;
        }
    }

    public int getAmountAfterDiscount() {
        int amountAfterDiscount = totalPrice - discountAmount;
        if (hasGift()) {
            amountAfterDiscount += GIFT_DISCOUNT_VALUE;
        }
        return amountAfterDiscount;
    }

    public boolean hasGift() {
        if (totalPrice >= MINIMUM_GIFT_DISCOUNT_VALUE) {
            return true;
        }
        return false;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }
}
