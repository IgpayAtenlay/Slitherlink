public enum EitherOr {
    EMPTY(" ", " ", " ", " "),
    ONE_OR_OTHER("/", "\\", "/", "\\"),
    BOTH_OR_NEITHER("⌜", "⌝", "⌟", "⌞");

    private final String northWest;
    private final String northEast;
    private final String southEast;
    private final String southWest;

    EitherOr(String northWest, String northEast, String southEast, String southWest) {
        this.northWest = northWest;
        this.northEast = northEast;
        this.southEast = southEast;
        this.southWest = southWest;
    }

    public String getNorthWest() {
        return northWest;
    }

    public String getNorthEast() {
        return northEast;
    }

    public String getSouthEast() {
        return southEast;
    }

    public String getSouthWest() {
        return southWest;
    }
}
