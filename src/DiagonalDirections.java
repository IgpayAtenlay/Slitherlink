public enum DiagonalDirections {

    NORTH_WEST(0, 0, 0, 1, 3, 2),
    NORTH_EAST(1, 1, 0, 2, 0, 3),
    SOUTH_EAST(2, 1, 1, 3, 1, 0),
    SOUTH_WEST(3, 0, 1, 0, 2, 1);

    private final int index;
    private final int xOffset;
    private final int yOffset;
    private final int clockwise;
    private final int counterClockwise;
    private final int opposite;

    DiagonalDirections(int index, int xOffset, int yOffset, int clockwise, int counterClockwise, int opposite) {
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

    public int getPointXOffset() {
        return xOffset;
    }

    public int getXOffset() {
        return getDirection().getXOffset()+getDirection().getCounterClockwise().getXOffset();
    }

    public int getYOffset() {
        return getDirection().getYOffset()+getDirection().getCounterClockwise().getYOffset();
    }

    public int getPointYOffset() {
        return yOffset;
    }

    public Direction getDirection() {
        if (index == Direction.NORTH.getIndex()) {
            return Direction.NORTH;
        } else if (index == Direction.EAST.getIndex()) {
            return Direction.EAST;
        } else if (index == Direction.SOUTH.getIndex()) {
            return Direction.SOUTH;
        } else {
            return Direction.WEST;
        }
    }

    public DiagonalDirections getClockwise() {
        if (this.clockwise == NORTH_WEST.index) {
            return NORTH_WEST;
        } else if (this.clockwise == SOUTH_EAST.index) {
            return SOUTH_EAST;
        } else if (this.clockwise == NORTH_EAST.index) {
            return NORTH_EAST;
        } else {
            return SOUTH_WEST;
        }
    }

    public DiagonalDirections getCounterClockwise() {
        if (this.counterClockwise == NORTH_WEST.index) {
            return NORTH_WEST;
        } else if (this.counterClockwise == SOUTH_EAST.index) {
            return SOUTH_EAST;
        } else if (this.counterClockwise == NORTH_EAST.index) {
            return NORTH_EAST;
        } else {
            return SOUTH_WEST;
        }
    }

    public DiagonalDirections getOpposite() {
        if (this.opposite == NORTH_WEST.index) {
            return NORTH_WEST;
        } else if (this.opposite == SOUTH_EAST.index) {
            return SOUTH_EAST;
        } else if (this.opposite == NORTH_EAST.index) {
            return NORTH_EAST;
        } else {
            return SOUTH_WEST;
        }
    }


}
