package logic;

import puzzle.Number;
import puzzle.*;

public class TwoLogic {

    public static void cornerTwo(int x, int y, Puzzle puzzle) {
        if (!(puzzle.getNumber(x, y) == Number.TWO)) {
            return;
        }

        for (DiagonalDirection diagonalDirection: DiagonalDirection.values()) {
            if (puzzle.getSlash(x, y, diagonalDirection, PointOrSquare.SQUARE) == SlashTypes.BOTH) {
                puzzle.setSlash(x, y, diagonalDirection.getOpposite(), PointOrSquare.SQUARE, SlashTypes.BOTH);
                puzzle.setSlash(x, y, diagonalDirection.getClockwise(), PointOrSquare.SQUARE, SlashTypes.SLASH);
                puzzle.setSlash(x, y, diagonalDirection.getCounterClockwise(), PointOrSquare.SQUARE, SlashTypes.SLASH);
            }
        }
    }

    public static void extendingSlash(int x, int y, Puzzle puzzle) {
        if (!(puzzle.getNumber(x, y) == Number.TWO)) {
            return;
        }

        for (DiagonalDirection diagonalDirection: DiagonalDirection.values()) {
            if (puzzle.getSlash(x, y, diagonalDirection, PointOrSquare.SQUARE) == SlashTypes.SLASH) {
                puzzle.setSlash(x, y, diagonalDirection.getOpposite(), PointOrSquare.SQUARE, SlashTypes.SLASH);
            }
        }
    }

    public static void oppositeX(int x, int y, Puzzle puzzle) {
        if (!(puzzle.getNumber(x, y) == Number.TWO)) {
            return;
        }

        for (Direction direction: Direction.values()) {
            if (puzzle.getLine(x, y, direction, PointOrSquare.SQUARE) == Line.X) {
//                dot 1
//                point at direction.getDiagonal.getCounterClockwise
//                get lines at direction.getOpposite and direction.getCounterClockwise
//                if one is a line
//                set direction.getDiagonal.getCounterClockwise to slash
                DiagonalDirection diagonal = direction.getDiagonal().getCounterClockwise();
                Line lineOne = puzzle.getLine(x + diagonal.getPointXOffset(), y + diagonal.getPointYOffset(), direction.getOpposite(), PointOrSquare.POINT);
                Line lineTwo = puzzle.getLine(x + diagonal.getPointXOffset(), y + diagonal.getPointYOffset(), direction.getCounterClockwise(), PointOrSquare.POINT);
                if (lineOne == Line.LINE || lineTwo == Line.LINE) {
                    puzzle.setSlash(x, y, diagonal, PointOrSquare.SQUARE, SlashTypes.SLASH);
                }

//                dot 2
                diagonal = direction.getDiagonal().getOpposite();
                lineOne = puzzle.getLine(x + diagonal.getPointXOffset(), y + diagonal.getPointYOffset(), direction.getOpposite(), PointOrSquare.POINT);
                lineTwo = puzzle.getLine(x + diagonal.getPointXOffset(), y + diagonal.getPointYOffset(), direction.getClockwise(), PointOrSquare.POINT);
                if (lineOne == Line.LINE || lineTwo == Line.LINE) {
                    puzzle.setSlash(x, y, diagonal, PointOrSquare.SQUARE, SlashTypes.SLASH);
                }

            }
        }
    }
}
