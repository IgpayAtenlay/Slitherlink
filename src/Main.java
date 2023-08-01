public class Main {

    public static void main(String[] args) {

        int puzzleNum = 23;

        Memory.addPuzzles();

        Puzzle activePuzzle = new Puzzle(Memory.getPuzzle(puzzleNum));
//        Puzzle activePuzzle = new Puzzle(7, 7);

        Logic.runLogic(activePuzzle);

        System.out.println(CheckDone.isDone(activePuzzle));

        System.out.println("Without slashes");
        System.out.println(activePuzzle.toString(false));
        System.out.println("With slashes");
        System.out.println(activePuzzle.toString(true));
    }
}