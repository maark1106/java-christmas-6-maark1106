package christmas.domain;

public class Discount {

    private int discountAmount;

    public Discount() {
        this.discountAmount = 0;
    }

    public boolean checkPresentationMenu(int totalPrice) {
        if(totalPrice >= 120000){
            discountAmount += 25000;
            return true;
        }
        return false;
    }
}
