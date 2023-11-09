package christmas.domain;

import java.util.List;

public enum CategoryMenu {

    APPETIZER(List.of("양송이수프", "타파스", "시저샐러드")),
    MAIN(List.of("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타")),
    DESSERT(List.of("초코케이크", "아이스크림")),
    BEVERAGE(List.of("제로콜라", "레드와인", "샴페인"));

    private final List<String> menus;

    CategoryMenu(List<String> menus) {
        this.menus = menus;
    }

    public List<String> getMenus() {
        return menus;
    }
}
