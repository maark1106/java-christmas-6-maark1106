package christmas.domain;

import java.util.Arrays;
import java.util.Map;

public enum MenuPrice {

    양송이수프(6000),
    타파스(5500),
    시저샐러드(8000),
    티본스테이크(55000),
    바비큐립(54000),
    해산물파스타(35000),
    크리스마스파스타(25000),
    초코케이크(15000),
    아이스크림(5000),
    제로콜라(3000),
    레드와인(60000),
    샴페인(25000);

    private int price;

    MenuPrice(int price) {
        this.price = price;
    }

    public static int calculatePrice(String menuName, Integer count) {
        for (MenuPrice menu : values()) {
            if (menu.name().equalsIgnoreCase(menuName)) {
                return menu.price * count;
            }
        }
        return 0;
    }

    public static int getOrderAmount(MyOrder myOrder) {
        Map<String, Integer> myOrders = myOrder.getMyOrders();
        return myOrders.entrySet()
                .stream()
                .mapToInt(entry -> MenuPrice.calculatePrice(entry.getKey(), entry.getValue()))
                .sum();
    }

    public int getPrice() {
        return price;
    }
}
