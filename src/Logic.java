public class Logic {

    public static void runLogic(Puzzle puzzle) {
        int numOfLogicRuns = 0;
        do {
            do {
                puzzle.setChanges(0);
                runThree(puzzle);
                runTwo(puzzle);
                runPerimeter(puzzle);
                runSlash(puzzle);
                runHighlight(puzzle);
                numOfLogicRuns++;
            } while (puzzle.getChanges() > 0);
            LoopLogic.checkForLoops(puzzle);
        } while (puzzle.getChanges() > 0);


        System.out.printf("Logic ran %d times%n%n", numOfLogicRuns);
    }

    public static void runThree(Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY(); y++) {
            for (int x = 0; x < puzzle.getSizeX(); x++) {
                if (puzzle.getNumber(x, y) == Number.THREE) {
                    ThreeLogic.adjacentThrees(x, y, puzzle);
                    ThreeLogic.diagonalThrees(x, y, puzzle);
                    ThreeLogic.lineIntoThrees(x, y, puzzle);
                }
            }
        }
    }

    public static void runTwo(Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY(); y++) {
            for (int x = 0; x < puzzle.getSizeX(); x++) {
                if (puzzle.getNumber(x, y) == Number.TWO) {
                    TwoLogic.cornerTwo(x, y, puzzle);
                    TwoLogic.extendingSlash(x, y, puzzle);
                    TwoLogic.oppositeX(x, y, puzzle);
                }
            }
        }
    }

    public static void runPerimeter(Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY(); y++) {
            for (int x = 0; x < puzzle.getSizeX(); x++) {
                PerimeterLogic.checkPerimeter(x, y, puzzle, PointOrSquare.SQUARE);
                PerimeterLogic.addNumber(x, y, puzzle);
            }
        }
        for (int y = 0; y < puzzle.getSizeY() + 1; y++) {
            for (int x = 0; x < puzzle.getSizeX() + 1; x++) {
                PerimeterLogic.checkPerimeter(x, y, puzzle, PointOrSquare.POINT);
            }
        }
    }

    public static void runSlash(Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY() + 1; y++) {
            for (int x = 0; x < puzzle.getSizeX() + 1; x++) {
                SlashLogic.addSlash(x, y, puzzle);
                SlashLogic.copySlash(x, y, puzzle);
                SlashLogic.useSlash(x, y, puzzle);
            }
        }

        for (int y = 0; y < puzzle.getSizeY(); y++) {
            for (int x = 0; x < puzzle.getSizeX(); x++) {
                SlashLogic.fillInSlash(x, y, puzzle);
            }
        }
    }

    public static void runHighlight(Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY(); y++) {
            for (int x = 0; x < puzzle.getSizeX(); x++) {
                HighlightLogic.addHighlight(x, y, puzzle);
                HighlightLogic.betweenHighlight(x, y, puzzle);
            }
        }
    }

}
