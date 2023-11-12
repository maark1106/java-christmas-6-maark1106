package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @DisplayName("날짜가 1~31 범위를 벗어났을 때 오류 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32, 101})
    void validateIsCorrectDateRangeTest(int date) {
        assertThatThrownBy(() -> new VisitDate(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주말인지 확인하는 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void isWeekendTest(int date) {
        VisitDate visitDate = new VisitDate(date);
        assertThat(visitDate.isWeekend())
                .isEqualTo(true);
    }
}