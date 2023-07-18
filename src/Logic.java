public class Logic {
    public static void runLogic(Grid grid) {
        runThrees(grid);
        runSquare(grid);
        runPoint(grid);
    }

    public static void runThrees(Grid grid) {
        for (int j = 0; j < grid.getSquareGrid()[0].length; j++) {
            for (int i = 0; i < grid.getSquareGrid().length; i++) {
                if (grid.getNumber(i, j) == Number.THREE) {
                    Threes.adjacentThrees(i, j, grid);
                    Threes.diagonalThrees(i, j, grid);
                }
            }
        }
    }

    public static void runSquare(Grid grid) {
        for (int j = 0; j < grid.getSquareGrid()[0].length; j++) {
            for (int i = 0; i < grid.getSquareGrid().length; i++) {
                grid.getSquareGrid()[i][j].checkPerimeter();
                grid.getSquareGrid()[i][j].testIsDone();
            }
        }
    }

    public static void runPoint(Grid grid) {
        for (int j = 0; j < grid.getPointGrid()[0].length; j++) {
            for (int i = 0; i < grid.getPointGrid().length; i++) {
                grid.getPointGrid()[i][j].checkPerimeter();
                grid.getPointGrid()[i][j].testIsDone();
                grid.getPointGrid()[i][j].logicAddSlash();
                grid.getPointGrid()[i][j].copySlash();
                grid.getPointGrid()[i][j].fillSlash();
            }
        }
    }
}
