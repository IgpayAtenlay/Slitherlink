public class Main {

    public static void main(String[] args) {

        int puzzleNum = 6;

        Memory.addPuzzles();

        Puzzle activePuzzle = new Puzzle(Memory.getPuzzle(puzzleNum));

        Logic.runLogic(activePuzzle);

        System.out.println("Without slashes");
        System.out.println(activePuzzle.toString(false));
        System.out.println("With slashes");
        System.out.println(activePuzzle.toString(true));
    }
}