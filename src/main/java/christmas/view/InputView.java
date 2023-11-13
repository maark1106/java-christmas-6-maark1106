package christmas.view;

import static christmas.util.InputValidator.validateOrderMenus;
import static christmas.util.InputValidator.validateVisitDate;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INPUT_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_ORDER_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static int inputVisitDate() {
        System.out.println(INPUT_VISIT_DATE_MESSAGE);
        String visitDate = Console.readLine();
        validateVisitDate(visitDate);
        return Integer.parseInt(visitDate);
    }

    public static String inputOrderMenus() {
        System.out.println(INPUT_ORDER_MENU_MESSAGE);
        String orderMenus = Console.readLine();
        validateOrderMenus(orderMenus);
        return orderMenus;
    }
}
