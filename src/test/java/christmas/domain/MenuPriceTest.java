package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuPriceTest {

    @DisplayName("메뉴 가격 계산하는 기능")
    @Test
    void calculatePriceTest() {
        String menuName = "양송이수프";
        int count = 3;
        assertThat(MenuPrice.calculatePrice(menuName,count))
                .isEqualTo(18000);
    }
}