public class Main {

    public static void main(String[] args) {
        PuzzleStorage.addPuzzles();
        Grid grid = new Grid(7, 7);
        grid.ringOfX();
        PopulateGrid.addNumberArray(grid, PuzzleStorage.getPuzzle(0));
        for (int i = 0; i < 10; i++) {
            Logic.runLogic(grid);
        }
        System.out.println(grid);
    }
}