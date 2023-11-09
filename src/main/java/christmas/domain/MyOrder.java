package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyOrder {

    private static final Pattern ORDER_PATTERN = Pattern.compile("^(.+)-(\\d+)$");
    private final Map<String, Integer> myOrders = new HashMap<>();

    public MyOrder(String inputOrders) {
        String[] orders = inputOrders.split(",");
        inputParseAndAddOrder(orders);
        validateMyOrder(myOrders);
    }

    public boolean isValidOrderFormat(String order) {
        Matcher matcher = ORDER_PATTERN.matcher(order);
        return matcher.matches();
    }

    private void inputParseAndAddOrder(String[] orders) {
        for (String order : orders) {
            if (!isValidOrderFormat(order)) {
                throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            String[] orderParts = order.split("-");
            String menuName = orderParts[0].trim();
            int quantity = Integer.parseInt(orderParts[1].trim());
            addOrder(menuName, quantity);
        }
    }

    private void addOrder(String menuName, int quantity) {
        if (myOrders.containsKey(menuName)) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        myOrders.put(menuName, quantity);
    }

    private void validateMyOrder(Map<String, Integer> myOrders) {
        orderCountLessThanOne(myOrders);
        totalMenuOverTwenty(myOrders);
    }

    private void orderCountLessThanOne(Map<String, Integer> myOrders) {
        myOrders.entrySet()
                .stream()
                .filter(entry -> entry.getValue() < 1)
                .forEach(entry -> {
                    throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
                });
    }

    private void totalMenuOverTwenty(Map<String, Integer> myOrders) {
        myOrders.entrySet()
                .stream()
                .filter(entry -> entry.getValue() < 1)
                .forEach(entry -> {
                    throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
                });
    }

    public Map<String, Integer> getMyOrders() {
        return myOrders;
    }
}
