package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
    @ValueSource(strings = {"티본스테이크", "타파스", "초코케이크","제로콜라"})
    void validateValidMenuTest(String menuName) {
        assertThatCode(() -> CategoryMenu.validateAvailableMenu(menuName))
                .doesNotThrowAnyException();
    }
}