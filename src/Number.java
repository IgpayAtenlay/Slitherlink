public enum Number {
    ZERO(0, "0"),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    EMPTY(-1, " ");

    private final int value;
    private final String str;

    Number(int value, String str) {
        this.value = value;
        this.str = str;
    }

    public int getValue() {
        return value;
    }

    public String getStr() {
        return str;
    }

    public boolean isEmpty() {
        return value == -1;
    }
}
