package christmas.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyOrder {

    private static final Pattern ORDER_PATTERN = Pattern.compile("^(.+)-(\\d+)$");
    private Map<String, Integer> myOrders = new HashMap<>();

    public MyOrder(String inputOrders) {
        parseOrdersByFormat(inputOrders);
        validateMyOrder(myOrders);
    }

    private void parseOrdersByFormat(String inputOrders) {
        for (String order : inputOrders.split(",")) {
            validOrderFormat(order);
            addAfterParse(order);
        }
    }

    public void validOrderFormat(String order) {
        Matcher matcher = ORDER_PATTERN.matcher(order);
        if(!matcher.matches()) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void addAfterParse(String order) {
        String[] orderParts =
                Arrays.stream(order.split("-"))
                .map(String::trim)
                .toArray(String[]::new);

        addOrder(orderParts[0], Integer.parseInt(orderParts[1]));
    }

    private void addOrder(String menuName, int quantity) {
        if(myOrders.containsKey(menuName)){
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        myOrders.put(menuName, quantity);
    }

    private void validateMyOrder(Map<String, Integer> myOrders) {
        validateOrderCountLessThanOne(myOrders);
        validateExceedTotalMenuTwenty(myOrders);
    }

    private void validateOrderCountLessThanOne(Map<String, Integer> myOrders) {
        myOrders.entrySet()
                .stream()
                .filter(entry -> entry.getValue() < 1)
                .forEach(entry -> {
                    throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
                });
    }

    private void validateExceedTotalMenuTwenty(Map<String, Integer> myOrders) {
        if (getTotalOrderCount(myOrders) > 20) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private int getTotalOrderCount(Map<String, Integer> myOrders) {
        return myOrders.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<String, Integer> getMyOrders()  {
        return myOrders;
    }
}
