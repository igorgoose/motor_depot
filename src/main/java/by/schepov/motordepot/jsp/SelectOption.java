package by.schepov.motordepot.jsp;

public enum SelectOption {
    ANY(0),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    TWO_HUNDRED(200),
    THREE_HUNDRED(300),
    FOUR_HUNDRED(400),
    FIVE_HUNDRED(500),
    SIX_HUNDRED(600),
    SEVEN_HUNDRED(700),
    EIGHT_HUNDRED(800),
    NINE_HUNDRED(900);

    private int value;

    SelectOption(int value)
    {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
