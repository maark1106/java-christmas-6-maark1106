package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {

    @DisplayName("할인 금액이 5000원 미만이면 배지 x")
    @ParameterizedTest
    @ValueSource(ints = {0, 1300, 4900})
    void getNotEventBadge(int discountAmount) {
        assertThat(Badge.getEventBadge(discountAmount))
                .isEqualTo(null);
    }

    @DisplayName("할인 금액이 5000원 이상, 10000원 미만이면 별 뱃지")
    @ParameterizedTest
    @ValueSource(ints = {5000, 7500, 9900})
    void getStarEventBadge(int discountAmount) {
        assertThat(Badge.getEventBadge(discountAmount))
                .isEqualTo(Badge.별);
    }

    @DisplayName("할인 금액이 10000원 이상, 20000원 미만이면 트리 뱃지")
    @ParameterizedTest
    @ValueSource(ints = {10000, 15000, 19900})
    void getTreeEventBadge(int discountAmount) {
        assertThat(Badge.getEventBadge(discountAmount))
                .isEqualTo(Badge.트리);
    }

    @DisplayName("할인 금액이 20000원 이상이면 산타 뱃지")
    @ParameterizedTest
    @ValueSource(ints = {20000, 100000, 100000000})
    void getSantaEventBadge(int discountAmount) {
        assertThat(Badge.getEventBadge(discountAmount))
                .isEqualTo(Badge.산타);
    }
}