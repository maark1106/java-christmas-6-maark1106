package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum CategoryMenu {

    APPETIZER(List.of("양송이수프", "타파스", "시저샐러드")),
    MAIN(List.of("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타")),
    DESSERT(List.of("초코케이크", "아이스크림")),
    BEVERAGE(List.of("제로콜라", "레드와인", "샴페인"));

    private final List<String> menus;

    CategoryMenu(List<String> menus) {
        this.menus = menus;
    }

    public static void validateMyOrder(MyOrder myOrder) {
        Map<String, Integer> myOrders = myOrder.getMyOrders();
        for (String menuName : myOrders.keySet()) {
            validateAvailableMenu(menuName);
        }
        validateOrderOnlyDrinks(myOrders);
    }

    public static void validateAvailableMenu(String menuName){
        Arrays.stream(CategoryMenu.values())
                .filter(category -> category.menus.contains(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }

    private static void validateOrderOnlyDrinks(Map<String, Integer> myOrders) {
        int beverageCounts = getBeverageCount(myOrders);
        if(beverageCounts != myOrders.size()){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static int getBeverageCount(Map<String, Integer> myOrders) {
        return myOrders.entrySet()
                .stream()
                .filter(entry -> CategoryMenu.BEVERAGE.menus.contains(entry.getKey()))
                .mapToInt(entry -> entry.getValue())
                .sum();
    }

    public static int getDessertCount(Map<String, Integer> myOrders) {
        return myOrders.entrySet()
                .stream()
                .filter(entry -> CategoryMenu.DESSERT.menus.contains(entry.getKey()))
                .mapToInt(entry -> entry.getValue())
                .sum();
    }

    public static int getMainCount(Map<String, Integer> myOrders) {
        return myOrders.entrySet()
                .stream()
                .filter(entry -> CategoryMenu.MAIN.menus.contains(entry.getKey()))
                .mapToInt(entry -> entry.getValue())
                .sum();
    }

    public List<String> getMenus() {
        return menus;
    }
}
