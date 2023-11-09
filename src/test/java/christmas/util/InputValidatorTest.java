package christmas.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @DisplayName("정수가 아니면 예외 발생")
    @Test
    void inputNotIntegerTypeExceptionTest() {
        assertThatThrownBy(() -> InputValidator.validateVisitDate("12a"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}