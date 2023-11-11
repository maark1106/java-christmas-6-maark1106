package christmas.domain;

import java.util.Arrays;

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
        return Arrays.stream(values())
                .filter(menu -> menu.name().equalsIgnoreCase(menuName))
                .findFirst()
                .map(menu -> menu.price * count)
                .orElse(0);
    }

    public static int getOrderAmount(MyOrder myOrder) {
        return myOrder.getMyOrders()
                .entrySet()
                .stream()
                .mapToInt(entry -> MenuPrice.calculatePrice(entry.getKey(), entry.getValue()))
                .sum();
    }
}
