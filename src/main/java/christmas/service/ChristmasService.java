package christmas.service;

import christmas.domain.CategoryMenu;
import christmas.domain.MyOrder;
import java.util.Map;

public class ChristmasService {

    public void validateMyOrder(MyOrder myOrder) {
        Map<String, Integer> myOrders = myOrder.getMyOrders();
        for (String menuName : myOrders.keySet()) {
            CategoryMenu.validateAvailableMenu(menuName);
        }
    }
}
