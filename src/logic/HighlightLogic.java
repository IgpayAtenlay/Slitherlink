package logic;

import puzzle.*;

public class HighlightLogic {
        public static void addHighlight(int x, int y, Puzzle puzzle) {
            if (puzzle.getHighlightColor(x, y) == Highlight.EMPTY) {
                for (Direction direction: Direction.values()) {
                    if (puzzle.getHighlightColor(x + direction.getXOffset(), y + direction.getYOffset()) != Highlight.EMPTY && puzzle.getLine(x, y, direction, PointOrSquare.SQUARE) != Line.EMPTY){
                        if (puzzle.getLine(x, y, direction, PointOrSquare.SQUARE) == Line.LINE) {
                            puzzle.setHighlightColor(x, y, puzzle.getHighlightColor(x + direction.getXOffset(), y + direction.getYOffset()).getOpposite());
                        } else {
                            puzzle.setHighlightColor(x, y, puzzle.getHighlightColor(x + direction.getXOffset(), y + direction.getYOffset()));
                        }
                    }
                }
            }
        }

        public static void betweenHighlight(int x, int y, Puzzle puzzle) {
            if (puzzle.getHighlightColor(x, y) != Highlight.EMPTY) {
                for (Direction direction: Direction.values()) {
                    if (puzzle.getHighlightColor(x + direction.getXOffset(), y + direction.getYOffset()) != Highlight.EMPTY && puzzle.getLine(x, y, direction, PointOrSquare.SQUARE) == Line.EMPTY){
                        if (puzzle.getHighlightColor(x, y) == puzzle.getHighlightColor(x + direction.getXOffset(), y + direction.getYOffset())) {
                            puzzle.setLine(x, y, direction, PointOrSquare.SQUARE, Line.X);
                        } else {
                            puzzle.setLine(x, y, direction, PointOrSquare.SQUARE, Line.LINE);
                        }
                    }
                }
            }
        }
}
