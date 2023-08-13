import display.Display;
import logic.CheckDone;
import logic.Logic;
import puzzle.Puzzle;
import storage.Memory;

public class Main {

    public static void main(String[] args) {
//        automaticCheckPuzzles();
//        manualCheckPuzzles(18);
        paintPuzzle(18);
    }

    public static void automaticCheckPuzzles() {
        Memory.addPuzzles();
        Logic.checkSolvablePuzzles();
    }

    public static void manualCheckPuzzles(int puzzleNum) {

        Memory.addPuzzles();

        Puzzle activePuzzle = new Puzzle(puzzleNum);
//        Puzzle activePuzzle = new Puzzle(7, 7);

        System.out.printf("logic.Logic ran %d times%n%n", Logic.runLogic(activePuzzle));

        System.out.println(CheckDone.isDone(activePuzzle) ? "Finished" : "Not finished");
        System.out.println();

//        System.out.println("Without slashes");
        System.out.println(activePuzzle.toString(false));
//        System.out.println("With slashes");
        System.out.println(activePuzzle.toString(true));


    }

    public static void paintPuzzle(int puzzleNum) {
        Memory.addPuzzles();
        Puzzle activePuzzle = new Puzzle(puzzleNum);
        Logic.runLogic(activePuzzle);
        Display.paintPuzzle(activePuzzle, true);
    }
}