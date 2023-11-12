package christmas.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import christmas.domain.MyOrder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @DisplayName("방문 날짜 입력 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "12a", "날짜", "11.1"})
    void validateVisitDateTest(String visitDate) {
        assertThatThrownBy(() -> InputValidator.validateVisitDate(visitDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴 입력 예외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프2", "양송이수프--2", "양송이수프*3,초코케이크-2",
    "양송이수프","양송이수프3-","양송이수프-2,"})
    void validOrderFormatTest(String orderMenus) {
        assertThatThrownBy(() -> new MyOrder(orderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}