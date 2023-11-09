package christmas.domain;

import java.util.List;
import java.util.Map;

public class Discount {

    private int discountAmount;
    private static final List<Integer> specialDiscountDays = List.of(3,10,17,24,25,31);

    public Discount() {
        this.discountAmount = 0;
    }

    public boolean checkPresentationMenu(int totalPrice) {
        if(totalPrice >= 120000){
            return true;
        }
        return false;
    }

    public void checkSpecialDiscountDays(Map<String, Integer> benefitStorage, Date date){
        if(specialDiscountDays.contains(date.getDate())){
            benefitStorage.put("크리스마스 디데이 할인: ", 100*date.getDate());
        }
    }
}
