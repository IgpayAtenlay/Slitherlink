public class Square {

    private Line[] lines;
    private Slash[] slashes;
    private Number number;
    private Highlight highlight;
    private boolean done;

    public Square(Line[] lines, Slash[] slashes, Number number, Highlight highlight) {
        this.lines = lines;
        this.slashes = slashes;
        this.number = number;
        this.highlight = highlight;
        this.done = false;
    }

    public Square(Line[] lines, Slash[] slashes) {
        this(lines,slashes, Number.EMPTY, Highlight.EMPTY);
    }

    public Number getNumber() {
        return number;
    }

    public String getNumberString() {
        return number.getStr();
    }

    public Line getLine(Direction direction) {
        return lines[direction.getIndex()];
    }

    public Slash getSlash(DiagonalDirections diagonalDirections) {
        return slashes[diagonalDirections.getIndex()];
    }

    public boolean isDone() {
        return done;
    }

    public void setLine(Direction direction, LineOrX lineOrX) {
        lines[direction.getIndex()].setProtectedLineOrX(lineOrX);
    }

    public void setSlash(DiagonalDirections diagonalDirections, EitherOr eitherOr) {
        slashes[diagonalDirections.getIndex()].setProtectedEitherOr(eitherOr);
    }

    public void setNumber(Number number) {
        this.number = number;
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
            if (numOfLines == number.getValue() && !(numOfX == 4 - number.getValue())) {
                for (Direction direction : Direction.values()) {
                    setLine(direction, LineOrX.X);
                }
            } else if (numOfX == 4 - number.getValue() && !(numOfLines == number.getValue())) {
                for (Direction direction : Direction.values()) {
                    setLine(direction, LineOrX.LINE);
                }
            } else if (numOfLines + numOfX < 2) {
                for (DiagonalDirections diagonalDirections : DiagonalDirections.values()) {
                    Line oppositeSideOne = getLine(diagonalDirections.getDirection().getOpposite());
                    Line oppositeSideTwo = getLine(diagonalDirections.getDirection().getClockwise());
                    numOfLines = 0;
                    numOfX = 0;
                    if (oppositeSideOne.getLineOrX() == LineOrX.LINE) {
                        numOfLines++;
                    } else if (oppositeSideOne.getLineOrX() == LineOrX.X) {
                        numOfX++;
                    }
                    if (oppositeSideTwo.getLineOrX() == LineOrX.LINE) {
                        numOfLines++;
                    } else if (oppositeSideTwo.getLineOrX() == LineOrX.X) {
                        numOfX++;
                    }
                    if (getSlash(diagonalDirections).getEitherOr() == EitherOr.ONE_OR_OTHER) {
                        if (numOfLines + 1 == getNumber().getValue()) {
                            oppositeSideOne.setProtectedLineOrX(LineOrX.X);
                            oppositeSideTwo.setProtectedLineOrX(LineOrX.X);
                        }
                        if (numOfX + 1 == 4 - getNumber().getValue()) {
                            oppositeSideOne.setProtectedLineOrX(LineOrX.LINE);
                            oppositeSideTwo.setProtectedLineOrX(LineOrX.LINE);
                        }
                    } else if (getSlash(diagonalDirections).getEitherOr() == EitherOr.BOTH_OR_NEITHER) {
                        if (numOfLines + 2 > getNumber().getValue()) {
                            getLine(diagonalDirections.getDirection()).setProtectedLineOrX(LineOrX.X);
                            getLine(diagonalDirections.getDirection().getClockwise()).setProtectedLineOrX(LineOrX.X);
                        } else if (numOfX + 2 > 4 - getNumber().getValue()) {
                            getLine(diagonalDirections.getDirection()).setProtectedLineOrX(LineOrX.LINE);
                            getLine(diagonalDirections.getDirection().getClockwise()).setProtectedLineOrX(LineOrX.LINE);
                        }
                    }
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
            if (numOfLines == number.getValue() && numOfX == number.getValue()) {
                done = true;
            }
        }
    }
}
