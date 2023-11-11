package christmas.domain;

import static christmas.domain.CategoryMenu.getDessertCount;
import static christmas.domain.CategoryMenu.getMainCount;

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

    public Map<String, Integer> storeBenefits(MyOrder myOrder, VisitDate visitDate, Discount discountInformation) {
        Map<String, Integer> benefitStorage = new HashMap<>();
        if(totalPrice >= 10000) {
            discountInformation.checkChristmasDDayDiscount(benefitStorage, visitDate);
            discountInformation.checkWeekDayDiscount(benefitStorage, getDessertCount(myOrder.getMyOrders()), visitDate);
            discountInformation.checkWeekendDiscount(benefitStorage, getMainCount(myOrder.getMyOrders()), visitDate);
            discountInformation.checkSpecialDayDiscount(benefitStorage, visitDate);
            discountInformation.checkPresentDiscount(benefitStorage, discountInformation);
        }
        return benefitStorage;
    }

    private void checkChristmasDDayDiscount(Map<String, Integer> benefitStorage, VisitDate visitDate) {
        if (visitDate.getDate() <= 25) {
            benefitStorage.put("크리스마스 디데이 할인", 1000 + 100 * (visitDate.getDate() - 1));
        }
    }

    private void checkWeekDayDiscount(Map<String, Integer> benefitStorage, int dessertCount, VisitDate visitDate) {
        if (!visitDate.isWeekend()) {
            benefitStorage.put("평일 할인", 2023 * dessertCount);
        }
    }

    private void checkWeekendDiscount(Map<String, Integer> benefitStorage, int mainCount, VisitDate visitDate) {
        if (visitDate.isWeekend()) {
            benefitStorage.put("주말 할인", 2023 * mainCount);
        }
    }

    private void checkSpecialDayDiscount(Map<String, Integer> benefitStorage, VisitDate visitDate) {
        if(specialDiscountDays.contains(visitDate.getDate())){
            benefitStorage.put("특별 할인", 1000);
        }
    }

    private void checkPresentDiscount(Map<String, Integer> benefitStorage, Discount discountInformation) {
        if(discountInformation.hasGift()){
            benefitStorage.put("증정 이벤트", 25000);
        }
    }

    public void addDiscountAmount(Map<String, Integer> benefitStorage) {
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
