package christmas.service;

import christmas.domain.CategoryMenu;
import christmas.domain.Date;
import christmas.domain.Discount;
import christmas.domain.MenuPrice;
import christmas.domain.MyOrder;
import java.util.Map;

public class ChristmasService {

    public void validateMyOrder(MyOrder myOrder) {
        Map<String, Integer> myOrders = myOrder.getMyOrders();
        for (String menuName : myOrders.keySet()) {
            CategoryMenu.validateAvailableMenu(menuName);
        }
    }

    public int getOrderAmount(MyOrder myOrder) {
        Map<String, Integer> myOrders = myOrder.getMyOrders();
        return myOrders.entrySet()
                .stream()
                .mapToInt(entry -> MenuPrice.calculatePrice(entry.getKey(), entry.getValue()))
                .sum();
    }

}
