public class PuzzleStorage {
    private static int[][][] puzzle;

    public static void addPuzzles() {
        puzzle = new int[20][][];
        puzzle[0] = new int[][] {
                {-1, -1, 3, 3, -1, -1, 3},
                {2, -1, 0, 2, -1, -1, 3},
                {2, 3, 2, -1, -1, -1, 2},
                {2, -1, 1, 3, 2, 2, 2},
                {3, -1, -1, 3, -1, 2, -1},
                {-1, -1, -1, -1, 1, -1, -1},
                {1, -1, -1, -1, 3, 3, -1}
        };
    }

    public static int[][] getPuzzle(int puzzleNum) {
        return puzzle[puzzleNum];
    }
}
