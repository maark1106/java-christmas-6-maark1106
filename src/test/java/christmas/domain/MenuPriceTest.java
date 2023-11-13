package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuPriceTest {

    @DisplayName("메뉴 가격 계산하는 기능")
    @Test
    void calculatePriceTest() {
        String menuName = "양송이수프";
        int count = 3;
        assertThat(MenuPrice.calculatePrice(menuName, count))
                .isEqualTo(18000);
    }

    @DisplayName("할인 전 총 주문 금액 계산하는 기능")
    @Test
    void getOrderAmountTest() {
        MyOrder myOrder = new MyOrder("티본스테이크-2,초코케이크-2,제로콜라-1");
        assertThat(MenuPrice.getOrderAmount(myOrder))
                .isEqualTo(143000);
    }
}