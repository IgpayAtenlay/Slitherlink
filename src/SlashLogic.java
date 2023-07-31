public class SlashLogic {

    public static void addSlash(int x, int y, Puzzle puzzle) {
        for (DiagonalDirection diagonalDirection : DiagonalDirection.values()) {
            if (puzzle.getSlash(x, y, diagonalDirection, PointOrSquare.POINT) == SlashTypes.EMPTY) {
                Line one = puzzle.getLine(x, y, diagonalDirection.getDirection(), PointOrSquare.POINT);
                Line two = puzzle.getLine(x, y, diagonalDirection.getDirection().getCounterClockwise(), PointOrSquare.POINT);
                if (!(one == Line.EMPTY) && !(two == Line.EMPTY)) {
                    if ((one == Line.LINE && two == Line.LINE) || (one == Line.X && two == Line.X)) {
                        puzzle.setSlash(x, y, diagonalDirection, PointOrSquare.POINT, SlashTypes.BOTH_OR_NEITHER);
                    } else {
                        puzzle.setSlash(x, y, diagonalDirection, PointOrSquare.POINT, SlashTypes.ONE_OR_OTHER);
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
}
