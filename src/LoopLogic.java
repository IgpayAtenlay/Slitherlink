public class LoopLogic {
    public static int[] findLoopStart(int startX, int startY, Puzzle puzzle) {
        int y = startY;
        for (int x = startX; x < puzzle.getSizeX() + 1; x++) {
            if (puzzle.getNumOfLines(x, y, PointOrSquare.POINT) == 1) {
                return new int[] {x, y};
            }
        }

        for (y = startY + 1; y < puzzle.getSizeY() + 1; y++) {
            for (int x = 0; x < puzzle.getSizeX() + 1; x++) {
                if (puzzle.getNumOfLines(x, y, PointOrSquare.POINT) == 1) {
                    return new int[] {x, y};
                }
            }
        }

        return new int[] {};
    }

    public static int[] findLoopStart(Puzzle puzzle) {
        return findLoopStart(0, 0, puzzle);
    }

    public static int[][] findAllLoopStarts(Puzzle puzzle) {
        int x = 0;
        int y = 0;
        int[][] loopStarts = new int[(puzzle.getSizeX() + 1) * (puzzle.getSizeY() + 1)][];
        int loopNum = 0;

//        find all loop starts

        while (x < puzzle.getSizeX() + 1 && y < puzzle.getSizeY() + 1) {
            loopStarts[loopNum] = findLoopStart(x, y, puzzle);
            if (loopStarts[loopNum].length == 0) {
                loopStarts[loopNum] = null;
                break;
            }
            if (loopStarts[loopNum][0] < puzzle.getSizeX()) {
                x = loopStarts[loopNum][0] + 1;
                y = loopStarts[loopNum][1];
            } else {
                x = 0;
                y = loopStarts[loopNum][1] + 1;
            }
            loopNum++;
        }

//        change array to proper length

        int length = 0;

        for (int[] loopStart : loopStarts) {
            if (loopStart == null) {
                break;
            } else {
                length++;
            }
        }

        int[][] cutLoop = new int[length][];

        for (int i = 0; i < length; i ++) {
            cutLoop[i] = loopStarts[i];
        }

        return cutLoop;
    }

    public static int[] startToEnd(int[] current, Puzzle puzzle) {
        int[] previous = current;
        int[] temp;
        do {
            temp = nextPoint(previous, current, puzzle);
            previous = current;
            current = temp;
        } while (puzzle.getNumOfLines(current[0], current[1], PointOrSquare.POINT) == 2);

        return current;
    }

    public static int[] nextPoint(int[] from, int[] current, Puzzle puzzle) {
        int[] newPoint = new int[2];
        if (puzzle.getNumOfLines(current[0], current[1], PointOrSquare.POINT) == 1) {
            for (Direction direction: Direction.values()) {
                if (puzzle.getLine(current[0], current[1], direction,PointOrSquare.POINT) == Line.LINE) {
                    newPoint[0] = current[0] + direction.getXOffset();
                    newPoint[1] = current[1] + direction.getYOffset();
                }
            }
        } else {
            for (Direction direction: Direction.values()) {
                if (puzzle.getLine(current[0], current[1], direction,PointOrSquare.POINT) == Line.LINE) {
                    if (!(from[0] == current[0] + direction.getXOffset() && from[1] == current[1] + direction.getYOffset())) {
                        newPoint[0] = current[0] + direction.getXOffset();
                        newPoint[1] = current[1] + direction.getYOffset();
                    }
                }
            }
        }

        return newPoint;
    }

    public static void checkForLoops(Puzzle puzzle) {
        int[][] allLoopStarts = findAllLoopStarts(puzzle);

        if (allLoopStarts.length > 2) {
//            do stuff

//            itterate through all starting points
            for (int[] start: allLoopStarts) {
//            follow the line
                int[] end = startToEnd(start, puzzle);
//            if adjacent, put x inbetween
                if (start[0] == end[0] && start[1] + 1 == end[1]) {
                    puzzle.setLine(start[0], start[1], Direction.SOUTH, PointOrSquare.POINT, Line.X);
                } else if (start[0] + 1 == end[0] && start[1] == end[1]) {
                    puzzle.setLine(start[0], start[1], Direction.EAST, PointOrSquare.POINT, Line.X);
                }
            }

        }
    }
}
