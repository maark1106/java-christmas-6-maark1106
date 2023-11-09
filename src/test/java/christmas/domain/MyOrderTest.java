package christmas.domain;


import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyOrderTest {

    @DisplayName("입력 받은 메뉴가 형식과 다르면 예외처리")
    @Test
    void validOrderFormatTest() {
        String menus = "해산물파스타-a,레드와인-1,초코케이크-1";
        assertThatThrownBy(() -> new MyOrder(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴 입력 시 예외 처리")
    @Test
    void orderDuplicateTest() {
        String menus = "해산물파스타-1,해산물파스타-1,초코케이크-1";
        assertThatThrownBy(() -> new MyOrder(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("개수 1미만 입력 시 예외처리")
    @Test
    void OrderCountLessThanOneTest() {
        String menus = "해산물파스타-0,해산물파스타-1,초코케이크-1";
        assertThatThrownBy(() -> new MyOrder(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모든 메뉴 개수 합이 20 초과시 예외 처리")
    @Test
    void totalMenuOverTwentyTest() {
        String menus = "해산물파스타-10,레드와인-8,초코케이크-3";
        assertThatThrownBy(() -> new MyOrder(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }
}