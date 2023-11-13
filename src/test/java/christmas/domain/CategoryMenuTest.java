package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CategoryMenuTest {

    @DisplayName("없는 메뉴 입력 시 예외처리")
    @ParameterizedTest
    @ValueSource(strings = {"김치", "오렌지", "똥냥꿍"})
    void validateInvalidMenuTest(String menuName) {
        assertThatThrownBy(() -> CategoryMenu.validateAvailableMenu(menuName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("있는 메뉴 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "타파스", "초코케이크", "제로콜라"})
    void validateValidMenuTest(String menuName) {
        assertThatCode(() -> CategoryMenu.validateAvailableMenu(menuName))
                .doesNotThrowAnyException();
    }

    @DisplayName("음료만 주문한 경우 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-3,레드와인-6", "제로콜라-1,레드와인-3,샴페인-6"})
    void validateOrderOnlyDrinksTest(String orderMenus) {
        assertThatThrownBy(() ->
                CategoryMenu.validateMyOrder(new MyOrder(orderMenus)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("디저트 주문 개수가 몇 개인지 테스트")
    @Test
    void getDessertCountTest() {
        HashMap<String, Integer> myOrders = new HashMap<>();
        myOrders.put("초코케이크", 3);
        myOrders.put("아이스크림", 2);
        myOrders.put("바비큐립", 2);
        assertThat(CategoryMenu.getDessertCount(myOrders))
                .isEqualTo(5);
    }

    @DisplayName("메인 주문 개수가 몇 개인지 테스트")
    @Test
    void getMainCountTest() {
        HashMap<String, Integer> myOrders = new HashMap<>();
        myOrders.put("티본스테이크", 10);
        myOrders.put("아이스크림", 2);
        myOrders.put("바비큐립", 2);
        assertThat(CategoryMenu.getMainCount(myOrders))
                .isEqualTo(12);
    }
}