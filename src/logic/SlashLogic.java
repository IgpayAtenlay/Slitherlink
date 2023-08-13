package logic;

import puzzle.*;

public class SlashLogic {

    public static void addSlash(int x, int y, Puzzle puzzle) {
        for (DiagonalDirection diagonalDirection : DiagonalDirection.values()) {
            if (puzzle.getSlash(x, y, diagonalDirection, PointOrSquare.POINT) == SlashTypes.EMPTY) {
                Line one = puzzle.getLine(x, y, diagonalDirection.getDirection(), PointOrSquare.POINT);
                Line two = puzzle.getLine(x, y, diagonalDirection.getDirection().getCounterClockwise(), PointOrSquare.POINT);
                if (!(one == Line.EMPTY) && !(two == Line.EMPTY)) {
                    if ((one == Line.LINE && two == Line.LINE) || (one == Line.X && two == Line.X)) {
                        puzzle.setSlash(x, y, diagonalDirection, PointOrSquare.POINT, SlashTypes.BOTH);
                    } else {
                        puzzle.setSlash(x, y, diagonalDirection, PointOrSquare.POINT, SlashTypes.SLASH);
                    }
                }
            }
        }
    }

    public static void copySlash(int x, int y, Puzzle puzzle) {
        for (DiagonalDirection diagonalDirection : DiagonalDirection.values()) {
            puzzle.setSlash(x, y, diagonalDirection, PointOrSquare.POINT, puzzle.getSlash(x, y, diagonalDirection.getOpposite(), PointOrSquare.POINT));
        }
    }

    public static void fillInSlash(int x, int y, Puzzle puzzle) {
        int numOfSlash = 0;
        int numOfBoth = 0;

        for (DiagonalDirection diagonalDirection : DiagonalDirection.values()) {
            if (puzzle.getSlash(x, y, diagonalDirection, PointOrSquare.SQUARE) == SlashTypes.SLASH) {
                numOfSlash++;
            } else if (puzzle.getSlash(x, y, diagonalDirection, PointOrSquare.SQUARE) == SlashTypes.BOTH) {
                numOfBoth++;
            }
        }

        if (numOfSlash == 3) {
            for (DiagonalDirection diagonalDirection : DiagonalDirection.values()) {
                puzzle.setSlash(x, y, diagonalDirection, PointOrSquare.SQUARE, SlashTypes.SLASH, true);
            }
        } else if (numOfBoth == 3) {
            for (DiagonalDirection diagonalDirection : DiagonalDirection.values()) {
                puzzle.setSlash(x, y, diagonalDirection, PointOrSquare.SQUARE, SlashTypes.BOTH, true);
            }
        }
    }

    public static void useSlash(int x, int y, Puzzle puzzle) {
        for (DiagonalDirection diagonalDirection: DiagonalDirection.values()) {
            if (puzzle.getSlash(x, y, diagonalDirection, PointOrSquare.POINT) != SlashTypes.EMPTY) {

                if (puzzle.getSlash(x, y, diagonalDirection, PointOrSquare.POINT) == SlashTypes.BOTH) {
                    puzzle.setLine(x, y, diagonalDirection.getDirection(), PointOrSquare.POINT, puzzle.getLine(x, y, diagonalDirection.getDirection().getCounterClockwise(), PointOrSquare.POINT), true);
                    puzzle.setLine(x, y, diagonalDirection.getDirection().getCounterClockwise(), PointOrSquare.POINT, puzzle.getLine(x, y, diagonalDirection.getDirection(), PointOrSquare.POINT), true);
                } else if (puzzle.getSlash(x, y, diagonalDirection, PointOrSquare.POINT) == SlashTypes.SLASH) {
                    puzzle.setLine(x, y, diagonalDirection.getDirection(), PointOrSquare.POINT, puzzle.getLine(x, y, diagonalDirection.getDirection().getCounterClockwise(), PointOrSquare.POINT).getOpposite(), true);
                    puzzle.setLine(x, y, diagonalDirection.getDirection().getCounterClockwise(), PointOrSquare.POINT, puzzle.getLine(x, y, diagonalDirection.getDirection(), PointOrSquare.POINT).getOpposite(), true);
                }

            }
        }
    }
}
