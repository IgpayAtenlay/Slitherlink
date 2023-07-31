public enum SlashTypes {
    EMPTY(" ", " ", " ", " "),
    ONE_OR_OTHER("/", "\\", "/", "\\"),
    BOTH_OR_NEITHER("⌜", "⌝", "⌟", "⌞");

    private final String northWest;
    private final String northEast;
    private final String southEast;
    private final String southWest;

    SlashTypes(String northWest, String northEast, String southEast, String southWest) {
        this.northWest = northWest;
        this.northEast = northEast;
        this.southEast = southEast;
        this.southWest = southWest;
    }

    public String getString(DiagonalDirection diagonalDirection) {
        if (diagonalDirection == DiagonalDirection.NORTH_WEST) {
            return northWest;
        } else if (diagonalDirection == DiagonalDirection.NORTH_EAST) {
            return northEast;
        } else if (diagonalDirection == DiagonalDirection.SOUTH_EAST) {
            return southEast;
        } else {
            return southWest;
        }
    }

}
