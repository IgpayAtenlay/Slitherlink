package logic;

import puzzle.*;
import puzzle.Number;

public class ThreeLogic {

    public static void adjacentThrees(int x, int y, Puzzle puzzle) {
        if (!(puzzle.getNumber(x, y) == Number.THREE)) {
            return;
        }
        for (Direction direction: Direction.values()) {
            if (puzzle.getNumber(x + direction.getXOffset(), y + direction.getYOffset()) == Number.THREE) {
                puzzle.setLine(x, y, direction, PointOrSquare.SQUARE, Line.LINE);
                puzzle.setLine(x, y, direction.getOpposite(), PointOrSquare.SQUARE, Line.LINE);
                puzzle.setLine(x + direction.getClockwise().getXOffset(), y + direction.getClockwise().getYOffset(), direction, PointOrSquare.SQUARE, Line.X);
            }
        }
    }

    public static void diagonalThrees(int x, int y, Puzzle puzzle) {
        if (!(puzzle.getNumber(x, y) == Number.THREE)) {
            return;
        }
        for (DiagonalDirection diagonalDirection : DiagonalDirection.values()) {
            if (puzzle.getNumber(x + diagonalDirection.getXOffset(), y + diagonalDirection.getYOffset()) == Number.THREE) {
                puzzle.setSlash(x, y, diagonalDirection, PointOrSquare.SQUARE, SlashTypes.SLASH);
            }
        }
    }

    public static void lineIntoThrees(int x, int y, Puzzle puzzle) {
        if (!(puzzle.getNumber(x, y) == Number.THREE)) {
            return;
        }
        for (DiagonalDirection diagonalDirection : DiagonalDirection.values()) {
            int modifiedX = x + diagonalDirection.getPointXOffset();
            int modifiedY = y + diagonalDirection.getPointYOffset();
            if (puzzle.getLine(modifiedX, modifiedY, diagonalDirection.getDirection(), PointOrSquare.POINT) == Line.LINE || puzzle.getLine(modifiedX, modifiedY, diagonalDirection.getDirection().getCounterClockwise(), PointOrSquare.POINT) == Line.LINE) {
                puzzle.setLine(modifiedX, modifiedY, diagonalDirection.getDirection(), PointOrSquare.POINT, Line.X);
                puzzle.setLine(modifiedX, modifiedY, diagonalDirection.getDirection().getCounterClockwise(), PointOrSquare.POINT, Line.X);
            }
        }
    }
}