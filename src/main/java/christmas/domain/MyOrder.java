package christmas.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyOrder {

    private static final String INVALID_ORDER_EXCEPTION_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_PATTERN = "^(.+)-(\\d+)$";
    private static final String SEPARATE_ORDERS_REGEX = ",";
    private static final String SEPARATE_ORDER_TO_MENU_AND_COUNT_REGEX = "-";
    private static final int MINIMUM_ORDER_COUNT = 1;
    private static final int MAXIMUM_ORDER_COUNT = 20;

    private Map<String, Integer> myOrders = new HashMap<>();

    public MyOrder(String inputOrders) {
        parseOrdersByFormat(inputOrders);
        validateMyOrder(myOrders);
    }

    private void parseOrdersByFormat(String inputOrders) {
        for (String order : inputOrders.split(SEPARATE_ORDERS_REGEX)) {
            validOrderFormat(order);
            addAfterParse(order);
        }
    }

    public void validOrderFormat(String order) {
        Pattern pattern = Pattern.compile(ORDER_PATTERN);
        Matcher matcher = pattern.matcher(order);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private void addAfterParse(String order) {
        String[] orderParts =
                Arrays.stream(order.split(SEPARATE_ORDER_TO_MENU_AND_COUNT_REGEX))
                        .map(String::trim)
                        .toArray(String[]::new);

        addOrder(orderParts[0], Integer.parseInt(orderParts[1]));
    }

    private void addOrder(String menuName, int quantity) {
        if (myOrders.containsKey(menuName)) {
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
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
                .filter(entry -> entry.getValue() < MINIMUM_ORDER_COUNT)
                .forEach(entry -> {
                    throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
                });
    }

    private void validateExceedTotalMenuTwenty(Map<String, Integer> myOrders) {
        if (getTotalOrderCount(myOrders) > MAXIMUM_ORDER_COUNT) {
            throw new IllegalArgumentException(INVALID_ORDER_EXCEPTION_MESSAGE);
        }
    }

    private int getTotalOrderCount(Map<String, Integer> myOrders) {
        return myOrders.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<String, Integer> getMyOrders() {
        return myOrders;
    }
}
