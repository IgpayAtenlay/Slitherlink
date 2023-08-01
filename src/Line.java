public enum Line {
    EMPTY("     ", " "),
    LINE("-----", "|"),
    X("  X  ", "X");

    private final String horzStr;
    private final String vertStr;

    Line(String horzStr, String vertStr) {
        this.horzStr = horzStr;
        this.vertStr = vertStr;
    }

    public String getHorzStr() {
        return horzStr;
    }

    public String getVertStr() {
        return getVertStr(true);
    }

    public Line getOpposite() {
        if (this == LINE) {
            return X;
        } else if (this == X) {
            return LINE;
        } else {
            return EMPTY;
        }
    }

    public String getVertStr(boolean isMiddle) {
        if (isMiddle) {
            return vertStr;
        } else if (this == X) {
            return " ";
        } else {
            return vertStr;
        }
    }
}
