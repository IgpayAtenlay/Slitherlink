public class Logic {

    public static void runLogic(Puzzle puzzle) {
        for(int i = 0; i < 20; i++) {
            runThrees(puzzle);
            runPerimeter(puzzle);
            runSlashLogic(puzzle);
        }
    }

    public static void runThrees(Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY(); y++) {
            for (int x = 0; x < puzzle.getSizeX(); x++) {
                if (puzzle.getNumber(x, y) == Number.THREE) {
                    ThreeCheck.adjacentThrees(x, y, puzzle);
                    ThreeCheck.diagonalThrees(x, y, puzzle);
                    ThreeCheck.lineIntoThrees(x, y, puzzle);
                }
            }
        }
    }

    public static void runPerimeter(Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY(); y++) {
            for (int x = 0; x < puzzle.getSizeX(); x++) {
                PerimeterCheck.checkPerimeter(x, y, puzzle, PointOrSquare.SQUARE);
            }
        }
        for (int y = 0; y < puzzle.getSizeY() + 1; y++) {
            for (int x = 0; x < puzzle.getSizeX() + 1; x++) {
                PerimeterCheck.checkPerimeter(x, y, puzzle, PointOrSquare.POINT);
            }
        }
    }

    public static void runSlashLogic(Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY() + 1; y++) {
            for (int x = 0; x < puzzle.getSizeX() + 1; x++) {
                SlashLogic.addSlash(x, y, puzzle);
                SlashLogic.copySlash(x, y, puzzle);

            }
        }
    }

}
