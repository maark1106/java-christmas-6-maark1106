package christmas.domain;

import java.util.Arrays;

public enum Badge {

    산타(20000),
    트리(10000),
    별(5000);

    private final int price;

    Badge(int price) {
        this.price = price;
    }

    public static Badge getEventBadge(int discountAmount) {
        return Arrays.stream(values())
                .filter(badge -> discountAmount >= badge.price)
                .findFirst()
                .orElse(null);
    }
}
