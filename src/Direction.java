public enum Direction {
    NORTH(0, 0, -1, 1, 3, 2),
    EAST(1, 1, 0, 2, 0, 3),
    SOUTH(2, 0, 1, 3, 1, 0),
    WEST(3, -1, 0, 0, 2, 1);

    private final int index;
    private final int xOffset;
    private final int yOffset;
    private final int clockwise;
    private final int counterClockwise;
    private final int opposite;

    Direction(int index, int xOffset, int yOffset, int clockwise, int counterClockwise, int opposite) {
        this.index = index;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.clockwise = clockwise;
        this.counterClockwise = counterClockwise;
        this.opposite = opposite;
    }

    public int getIndex() {
        return index;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public Direction getClockwise() {
        if (this.clockwise == NORTH.index) {
            return NORTH;
        } else if (this.clockwise == SOUTH.index) {
            return SOUTH;
        } else if (this.clockwise == EAST.index) {
            return EAST;
        } else {
            return WEST;
        }
    }

    public Direction getCounterClockwise() {
        if (this.counterClockwise == NORTH.index) {
            return NORTH;
        } else if (this.counterClockwise == SOUTH.index) {
            return SOUTH;
        } else if (this.counterClockwise == EAST.index) {
            return EAST;
        } else {
            return WEST;
        }
    }

    public Direction getOpposite() {
        if (this.opposite == NORTH.index) {
            return NORTH;
        } else if (this.opposite == SOUTH.index) {
            return SOUTH;
        } else if (this.opposite == EAST.index) {
            return EAST;
        } else {
            return WEST;
        }
    }

    public DiagonalDirection getDiagonal() {
        if (index == DiagonalDirection.NORTH_WEST.getIndex()) {
            return DiagonalDirection.NORTH_WEST;
        } else if (index == DiagonalDirection.NORTH_EAST.getIndex()) {
            return DiagonalDirection.NORTH_EAST;
        } else if (index == DiagonalDirection.SOUTH_EAST.getIndex()) {
            return DiagonalDirection.SOUTH_EAST;
        } else {
            return DiagonalDirection.SOUTH_WEST;
        }
    }
}
