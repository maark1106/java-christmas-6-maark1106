package christmas.domain;


import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyOrderTest {

    @DisplayName("입력 받은 메뉴가 형식과 다르면 예외처리")
    @Test
    void validOrderFormatTest() {
        String menus = "해산물파스타-1,레드와인-1,초코케이크-1";
        assertThatThrownBy(() -> new MyOrder(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }
}