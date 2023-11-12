package christmas.domain;

public enum Event {

    CHRISTMAS_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL_DAY("특별 할인"),
    GIFT("증정 이벤트");

    private final String eventName;

    Event(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
