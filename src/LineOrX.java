public enum LineOrX {
    EMPTY("     ", " "),
    LINE("-----", "|"),
    X("  X  ", "X");

    private final String horzStr;
    private final String vertStr;

    LineOrX(String horzStr, String vertStr) {
        this.horzStr = horzStr;
        this.vertStr = vertStr;
    }

    public String getHorzStr() {
        return horzStr;
    }

    public String getVertStr() {
        return vertStr;
    }
}
