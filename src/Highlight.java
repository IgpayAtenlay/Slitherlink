public enum Highlight {
    EMPTY(" "),
    INSIDE("I"),
    OUTSIDE("O");

    private final String str;

    Highlight(String str) {
        this.str = str;
    }

    public String getStr(){
        return str;
    }
    public Highlight getOpposite() {
        if (this == INSIDE) {
            return OUTSIDE;
        } else if (this == OUTSIDE) {
            return INSIDE;
        } else {
            return EMPTY;
        }
    }
}
