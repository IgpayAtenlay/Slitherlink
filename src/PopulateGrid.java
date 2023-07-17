public class PopulateGrid {

    public static void createRandomDisplay(Grid grid) {
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                if (Math.random() > 0.7) {
                    grid.getPointGrid()[i][j].setLine(Direction.SOUTH, LineOrX.LINE);
                } else if (Math.random() > 0.6) {
                    grid.getPointGrid()[i][j].setLine(Direction.SOUTH, LineOrX.X);
                }
                if (Math.random() > 0.7) {
                    grid.getPointGrid()[i][j].setLine(Direction.EAST, LineOrX.LINE);
                } else if (Math.random() > 0.6) {
                    grid.getPointGrid()[i][j].setLine(Direction.EAST, LineOrX.X);
                }
                if (Math.random() > 0.4) {
                    grid.getSquareGrid()[i][j].setNumber(Number.EMPTY);
                } else if (Math.random() > 0.6) {
                    grid.getSquareGrid()[i][j].setNumber(Number.ZERO);
                } else if (Math.random() > 0.6) {
                    grid.getSquareGrid()[i][j].setNumber(Number.ONE);
                } else if (Math.random() > 0.6) {
                    grid.getSquareGrid()[i][j].setNumber(Number.TWO);
                } else{
                    grid.getSquareGrid()[i][j].setNumber(Number.THREE);
                }
            }
        }
    }

    public static void addCustomNumbers(Grid grid) {
        grid.getSquareGrid()[5][5].setNumber(Number.THREE);
        grid.getSquareGrid()[6][5].setNumber(Number.THREE);
        grid.getSquareGrid()[9][3].setNumber(Number.THREE);
        grid.getSquareGrid()[9][4].setNumber(Number.THREE);
        grid.getSquareGrid()[0][0].setNumber(Number.THREE);
        grid.getSquareGrid()[1][0].setNumber(Number.THREE);
        grid.getSquareGrid()[11][11].setNumber(Number.THREE);
        grid.getSquareGrid()[12][10].setNumber(Number.THREE);
    }
}
