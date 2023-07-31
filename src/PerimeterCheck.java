public class PerimeterCheck {

    public static void checkPerimeter(int x, int y, Puzzle puzzle, PointOrSquare pointOrSquare) {
        if (pointOrSquare == PointOrSquare.SQUARE && puzzle.getNumber(x, y) == Number.EMPTY) {
            return;
        }

        int numOfLines = getNumOfLines(x, y, puzzle, pointOrSquare);
        int numOfX = getNumOfX(x, y, puzzle, pointOrSquare);

        int num;

        if (pointOrSquare == PointOrSquare.SQUARE) {
            num = puzzle.getNumberValue(x, y);
        } else if (numOfLines > 0) {
            num = 2;
        } else if (numOfX > 2) {
            num = 0;
        } else {
//            some fancy method
//            if the point doesn't have enough into to be all x or all lines
            return;
        }

        if (numOfLines == num && !(numOfX == 4 - num)) {
            for (Direction direction : Direction.values()) {
                puzzle.setLine(x, y, direction, pointOrSquare, Line.X, true);
            }
        } else if (numOfX == 4 - num && !(numOfLines == num)) {
            for (Direction direction : Direction.values()) {
                puzzle.setLine(x, y, direction, pointOrSquare, Line.LINE, true);
            }
        } else if (numOfLines + numOfX < 2) {
            for (DiagonalDirection diagonalDirection : DiagonalDirection.values()) {
                if (!(puzzle.getSlash(x, y, diagonalDirection, pointOrSquare) == SlashTypes.EMPTY)) {
                    Direction oppositeSideOneDirection = diagonalDirection.getDirection().getOpposite();
                    Direction oppositeSideTwoDirection = diagonalDirection.getDirection().getClockwise();

                    numOfLines = 0;
                    numOfX = 0;

                    if (puzzle.getLine(x, y, oppositeSideOneDirection, pointOrSquare) == Line.LINE) {
                        numOfLines++;
                    } else if (puzzle.getLine(x, y, oppositeSideOneDirection, pointOrSquare) == Line.X) {
                        numOfX++;
                    }

                    if (puzzle.getLine(x, y, oppositeSideTwoDirection, pointOrSquare) == Line.LINE) {
                        numOfLines++;
                    } else if (puzzle.getLine(x, y, oppositeSideTwoDirection, pointOrSquare) == Line.X) {
                        numOfX++;
                    }

                    if (puzzle.getSlash(x, y, diagonalDirection, pointOrSquare) == SlashTypes.ONE_OR_OTHER) {
                        if (numOfLines + 1 == num) {
                            puzzle.setLine(x, y, oppositeSideOneDirection, pointOrSquare, Line.X);
                            puzzle.setLine(x, y, oppositeSideTwoDirection, pointOrSquare, Line.X);
                        }
                        if (numOfX + 1 == 4 - num) {
                            puzzle.setLine(x, y, oppositeSideOneDirection, pointOrSquare, Line.LINE);
                            puzzle.setLine(x, y, oppositeSideTwoDirection, pointOrSquare, Line.LINE);
                        }
                    } else if (puzzle.getSlash(x, y, diagonalDirection, pointOrSquare) == SlashTypes.BOTH_OR_NEITHER) {
                        if (numOfLines + 2 > num) {
                            puzzle.setLine(x, y, diagonalDirection.getDirection(), pointOrSquare, Line.X);
                            puzzle.setLine(x, y, diagonalDirection.getDirection().getCounterClockwise(), pointOrSquare, Line.X);
                        } else if (numOfX + 2 > 4 - num) {
                            puzzle.setLine(x, y, diagonalDirection.getDirection(), pointOrSquare, Line.LINE);
                            puzzle.setLine(x, y, diagonalDirection.getDirection().getCounterClockwise(), pointOrSquare, Line.LINE);
                        }
                    }
                }
            }
        } else if (numOfLines + numOfX == 2) {
            if (numOfLines + 1 == num) {
                for (Direction direction : Direction.values()) {
                    if (puzzle.getLine(x, y, direction, pointOrSquare) == Line.EMPTY && puzzle.getLine(x, y, direction.getCounterClockwise(), pointOrSquare) == Line.EMPTY) {
                        puzzle.setSlash(x, y, direction.getDiagonal(), pointOrSquare, SlashTypes.ONE_OR_OTHER);
                    }
                }
            }
        }
    }


    public static int getNumOfLines(int x, int y, Puzzle puzzle, PointOrSquare pointOrSquare) {
        int numOfLines = 0;
        for (Direction direction: Direction.values()) {
            if (puzzle.getLine(x, y, direction, pointOrSquare) == Line.LINE) {
                numOfLines++;
            }
        }
        return numOfLines;
    }

    public static int getNumOfX(int x, int y, Puzzle puzzle, PointOrSquare pointOrSquare) {
        int numOfX = 0;
        for (Direction direction: Direction.values()) {
            if (puzzle.getLine(x, y, direction, pointOrSquare) == Line.X) {
                numOfX++;
            }
        }
        return numOfX;
    }

}
