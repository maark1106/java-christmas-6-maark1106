package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountTest {

    private MyOrder myOrderContainGift;
    private MyOrder myOrderNotContainGift;

    @BeforeEach
    void setUp(){
        myOrderContainGift = new MyOrder("양송이수프-2,티본스테이크-2,초코케이크-2,제로콜라-2");
        myOrderNotContainGift = new MyOrder("양송이수프-1,티본스테이크-1,초코케이크-1,제로콜라-1");
    }

    @DisplayName("크리스마스디데이-O,평일-O,주말-X,스페셜-O,증정이벤트-O")
    @Test
    void discountTest_1() {
        testDiscount(myOrderContainGift, 25, 33446);
    }

    @DisplayName("크리스마스디데이-O,평일-X,주말-O,스페셜-O,증정이벤트-O")
    @Test
    void discountTest_2() {
        testDiscount(myOrderContainGift, 17, 32646);
    }

    @DisplayName("크리스마스디데이-O,평일-O,주말-X,스페셜-X,증정이벤트-O")
    @Test
    void discountTest_3(){
        testDiscount(myOrderContainGift, 18, 31746);
    }

    @DisplayName("크리스마스디데이-O,평일-O,주말-X,스페셜-O,증정이벤트-X")
    @Test
    void discountTest_4(){
        testDiscount(myOrderNotContainGift, 25, 6423);
    }

    @DisplayName("크리스마스디데이-O,평일-O,주말-X,스페셜-X,증정이벤트-X")
    @Test
    void discountTest_5(){
        testDiscount(myOrderNotContainGift, 18, 4723);
    }

    @DisplayName("크리스마스디데이-O,평일-O,주말-X,스페셜-X,증정이벤트-X")
    @Test
    void discountTest_6(){
        MyOrder myOrderContainOnlyChristmasDDayDiscount = new MyOrder("양송이수프-2");
        testDiscount(myOrderContainOnlyChristmasDDayDiscount, 11, 2000);
    }

    @DisplayName("크리스마스디데이-X,평일-X,주말-O,스페셜-O,증정이벤트-O")
    @Test
    void discountTest_7(){
        testDiscount(myOrderContainGift, 31, 30046);
    }

    @DisplayName("크리스마스디데이-X,평일-O,주말-X,스페셜-X,증정이벤트-O")
    @Test
    void discountTest_8(){
        testDiscount(myOrderContainGift, 26, 29046);
    }

    @DisplayName("크리스마스디데이-X,평일-X,주말-X,스페셜-X,증정이벤트-X")
    @Test
    void discountTest_9(){
        MyOrder myOrderNotContainAnyDiscount = new MyOrder("양송이수프-1");
        testDiscount(myOrderNotContainAnyDiscount, 26, 0);
    }

    @DisplayName("할인 전 금액이 120000원 이상일 경우 이벤트 증정")
    @Test
    void giveGiftTest(){
        Discount discountInformation = new Discount(130000);
        assertThat(discountInformation.hasGift())
                .isEqualTo(true);
    }

    private void testDiscount(MyOrder myOrder, int visitDate, int expectedAmount) {
        Discount discountInformation = new Discount(MenuPrice.getOrderAmount(myOrder));
        Map<Event, Integer> eventStorage = discountInformation.storeBenefits(myOrder,
                new VisitDate(visitDate), discountInformation);
        discountInformation.addDiscountAmount(eventStorage);
        assertThat(discountInformation.getDiscountAmount()).isEqualTo(expectedAmount);
    }
}