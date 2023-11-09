package christmas.view;

import christmas.domain.MyOrder;
import java.util.Map;

public class OutputView {

    public static void printWelcomeMessage(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printBenefitPreviewMessage(){
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] : " + errorMessage);
    }

    public static void printOrderMenu(MyOrder myOrder) {
        Map<String, Integer> myOrders = myOrder.getMyOrders();

        System.out.println("<주문 메뉴>");
        for (String menuName : myOrders.keySet()) {
            System.out.println(menuName + " " + myOrders.get(menuName));
        }
    }
}
