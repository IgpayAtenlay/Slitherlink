public class Point {

    private Line[] lines = new Line[4];
    private Slash[] slashes = new Slash[4];
    private boolean done;

    public Point() {
        lines[0] = new Line();
        lines[1] = new Line();
        lines[2] = new Line();
        lines[3] = new Line();
        for (int i = 0; i < 4; i++) {
            slashes[i] = new Slash();
        }
        done = false;
    }

    public Point(Line[] lines) {
        this.lines = lines;
        for (int i = 0; i < 4; i++) {
            slashes[i] = new Slash();
        }
        done = false;
    }

    public Line getLine(Direction direction) {
        return lines[direction.getIndex()];
    }

    public Slash getSlashes(DiagonalDirections diagonalDirections) {
        return slashes[diagonalDirections.getIndex()];
    }

    public boolean isDone() {
        return done;
    }

    public void setLine(Direction direction, LineOrX lineOrX) {
        lines[direction.getIndex()].setProtectedLineOrX(lineOrX);
    }

    public void setSlashes(DiagonalDirections diagonalDirections, EitherOr eitherOr) {
        slashes[diagonalDirections.getIndex()].setProtectedEitherOr(eitherOr);
    }

    public void checkPerimeter() {
        if (!done) {
            int numOfLines = 0;
            int numOfX = 0;
            for (Direction direction : Direction.values()) {
                if (getLine(direction).getLineOrX() == LineOrX.LINE) {
                    numOfLines++;
                } else if (getLine(direction).getLineOrX() == LineOrX.X) {
                    numOfX++;
                }
            }
            if (numOfLines == 2 && !(numOfX == 2)) {
                for (Direction direction : Direction.values()) {
                    setLine(direction, LineOrX.X);
                }
            } else if (numOfX == 2 && numOfLines == 1) {
                for (Direction direction : Direction.values()) {
                    setLine(direction, LineOrX.LINE);
                }
            } else if (numOfX == 3) {
                for (Direction direction : Direction.values()) {
                    setLine(direction, LineOrX.X);
                }
            }
        }
    }

    public void testIsDone() {
        if (!done) {
            int numOfLines = 0;
            int numOfX = 0;
            for (Direction direction : Direction.values()) {
                if (getLine(direction).getLineOrX() == LineOrX.LINE) {
                    numOfLines++;
                } else if (getLine(direction).getLineOrX() == LineOrX.X) {
                    numOfX++;
                }
            }
            if (numOfLines + numOfX == 4) {
                done = true;
            }
        }
    }

    public void logicAddSlash() {
        if (!done) {
            for (DiagonalDirections diagonalDirections : DiagonalDirections.values()) {
                if (getSlashes(diagonalDirections).getEitherOr() == EitherOr.EMPTY) {
                    LineOrX one = getLine(diagonalDirections.getDirection()).getLineOrX();
                    LineOrX two = getLine(diagonalDirections.getDirection().getCounterClockwise()).getLineOrX();
                    if (!(one == LineOrX.EMPTY) && !(two == LineOrX.EMPTY)) {
                        if ((one == LineOrX.LINE && two == LineOrX.LINE) || (one == LineOrX.X && two == LineOrX.X)) {
                            getSlashes(diagonalDirections).setProtectedEitherOr(EitherOr.BOTH_OR_NEITHER);
                        } else {
                            getSlashes(diagonalDirections).setProtectedEitherOr(EitherOr.ONE_OR_OTHER);
                        }
                    }
                }
            }
        }
    }

    public void copySlash() {
        if (!done) {
            for (DiagonalDirections diagonalDirections : DiagonalDirections.values()) {
                if (getSlashes(diagonalDirections).getEitherOr() == EitherOr.EMPTY) {
                    setSlashes(diagonalDirections, getSlashes(diagonalDirections.getOpposite()).getEitherOr());
                }
            }
        }
    }

    public void fillSlash() {

    }
}
