public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(20, 20);
        grid.ringOfX();
        PopulateGrid.addCustomNumbers(grid);
        for (int i = 0; i < 5; i++) {
            Logic.runLogic(grid);
        }
//        Threes.diagonalThrees(11, 11, grid);
        System.out.println(grid);
    }
}