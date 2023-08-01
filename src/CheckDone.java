public class CheckDone {

    public static boolean isDone(Puzzle puzzle) {
        return  isSquaresCorrect(puzzle) && isOneLoop(puzzle);
    }

    public static boolean isSquaresCorrect(Puzzle puzzle) {
        boolean isSquaresCorrect = true;
        for (int y = 0; y < puzzle.getSizeY(); y++) {
            for (int x = 0; x < puzzle.getSizeX(); x++) {
                if (puzzle.getNumberValue(x, y) != puzzle.getNumOfLines(x, y, PointOrSquare.SQUARE)) {
                    isSquaresCorrect = false;
                }
            }
        }

        return isSquaresCorrect;
    }

    public static boolean isOneLoop(Puzzle puzzle) {
        int[][] loopOne = new int[(puzzle.getSizeX() + 1) * (puzzle.getSizeY() + 1)][];
//        find starting place
        loopOne[0] = findLoop(puzzle);
//        create an array with all dots in a loop
        loopOne = loopBackAround(loopOne, puzzle);
//        check if any dots outside of loop have lines
        return !isDotsOutsideArray(loopOne, puzzle);
    }

    public static int[] findLoop(Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY() + 1; y++) {
            for (int x = 0; x < puzzle.getSizeX() + 1; x++) {
                if (puzzle.getNumOfLines(x, y, PointOrSquare.POINT) == 2) {
                    return new int[] {x, y};
                }
            }
        }

        return new int[] {};
    }

    public static int[][] loopBackAround(int[][] loop, Puzzle puzzle) {
        int pointNum = 1;
        loop[pointNum] = LoopLogic.nextPoint(loop[pointNum - 1], loop[pointNum - 1], puzzle);
        while (loop[pointNum][0] != loop[0][0] || loop[pointNum][1] != loop[0][1]) {
            pointNum++;
            loop[pointNum] = LoopLogic.nextPoint(loop[pointNum - 2], loop[pointNum - 1], puzzle);
            if (puzzle.getNumOfLines(loop[pointNum][0], loop[pointNum][1], PointOrSquare.POINT) != 2) {
                break;
            }
        }

        int length = 0;

        for (int[] loopPoint : loop) {
            if (loopPoint == null) {
                break;
            } else {
                length++;
            }
        }

        int[][] cutLoop = new int[length][];

        for (int i = 0; i < length; i ++) {
            cutLoop[i] = loop[i];
        }

        return cutLoop;
    }

    public static boolean isDotsOutsideArray(int[][] loop, Puzzle puzzle) {
        for (int y = 0; y < puzzle.getSizeY() + 1; y++) {
            for (int x = 0; x < puzzle.getSizeX() + 1; x++) {
                if (puzzle.getNumOfLines(x, y, PointOrSquare.POINT) > 0 && !arrayContainsPoint(x, y, loop)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean arrayContainsPoint(int x, int y, int[][] loop) {
        for (int[] loopPoint: loop) {
            if (loopPoint[0] == x && loopPoint[1] == y) {
                return true;
            }
        }

        return false;
    }

}
