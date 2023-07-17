public class Threes {

    public static void adjacentThrees(int x, int y, Grid grid) {
        if (!(grid.getNumber(x, y) == Number.THREE)) {
            return;
        }
        for (Direction direction: Direction.values()) {
            if (grid.getNumber(x + direction.getXOffset(), y + direction.getYOffset()) == Number.THREE) {
                grid.setSquareLines(x, y, direction, LineOrX.LINE);
                grid.setSquareLines(x, y, direction.getOpposite(), LineOrX.LINE);
                grid.setSquareLines(x + direction.getClockwise().getXOffset(), y + direction.getClockwise().getYOffset(), direction, LineOrX.X);
            }
        }
    }

    public static void diagonalThrees(int x, int y, Grid grid) {
        if (!(grid.getNumber(x, y) == Number.THREE)) {
            return;
        }
        for (DiagonalDirections diagonalDirections: DiagonalDirections.values()) {
            if (grid.getNumber(x + diagonalDirections.getXOffset(), y + diagonalDirections.getYOffset()) == Number.THREE) {
                grid.setSquareSlash(x, y, diagonalDirections, EitherOr.ONE_OR_OTHER);
            }
        }
    }


}