public class Puzzle {

//    variables

    private final int sizeX;
    private final int sizeY;
    private Line[][] verticalLines;
    private Line[][] horizontalLines;
    private SlashTypes[][] slashes;
    private Number[][] numbers;

//    constructors

    public Puzzle(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        verticalLines = new Line[sizeY + 2][sizeX + 1];
        for (int y = 1; y < verticalLines.length - 1; y++) {
            for (int x = 0; x < verticalLines[0].length; x++) {
                verticalLines[y][x] = Line.EMPTY;
            }
        }
        for (int x = 0; x < verticalLines[0].length; x++) {
            verticalLines[0][x] = Line.X;
            verticalLines[verticalLines.length - 1][x] = Line.X;
        }

        horizontalLines = new Line[sizeY + 1][sizeX + 2];
        for (int y = 0; y < horizontalLines.length; y++) {
            for (int x = 1; x < horizontalLines[0].length - 1; x++) {
                horizontalLines[y][x] = Line.EMPTY;
            }
            horizontalLines[y][0] = Line.X;
            horizontalLines[y][horizontalLines[0].length - 1] = Line.X;
        }

        slashes = new SlashTypes[sizeY * 2 + 2][sizeX * 2 + 2];
        for (int y = 0; y < slashes.length; y++) {
            for (int x = 0; x < slashes[0].length; x++) {
                slashes[y][x] = SlashTypes.EMPTY;
            }
        }

        numbers = new Number[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                numbers[y][x] = Number.EMPTY;
            }
        }
    }

    public Puzzle(int[][] puzzle) {
        this(puzzle[0].length, puzzle.length);
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                numbers[y][x] = Number.getNumber(puzzle[y][x]);
            }
        }
    }

//    getters and setters

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Line getLine(int x, int y, boolean isHorizontal) {
        if (isHorizontal && (x >= horizontalLines[0].length || x < 0 || y >= horizontalLines.length || y <0)) {
            return Line.X;
        } else if (!isHorizontal && (x >= verticalLines[0].length || x < 0 || y >= verticalLines.length || y <0)) {
            return Line.X;
        } else if (isHorizontal) {
            return horizontalLines[y][x];
        } else {
            return verticalLines[y][x];
        }
    }

    public Line getLine(int x, int y, Direction direction, PointOrSquare pointOrSquare) {
        if (pointOrSquare == PointOrSquare.SQUARE) {
            if (direction == Direction.NORTH) {
                return getLine(x + 1, y, true);
            } else if (direction == Direction.EAST) {
                return getLine(x + 1, y + 1, false);
            } else if (direction == Direction.SOUTH) {
                return getLine(x + 1, y + 1, true);
            } else {
                return getLine(x, y + 1, false);
            }
        } else {
            if (direction == Direction.NORTH) {
                return getLine(x, y, false);
            } else if (direction == Direction.EAST) {
                return getLine(x + 1, y, true);
            } else if (direction == Direction.SOUTH) {
                return getLine(x, y + 1, false);
            } else {
                return getLine(x, y, true);
            }
        }
    }

    public void setLine(int x, int y, boolean isHorizontal, Line line, boolean isProtected) {
        if (isHorizontal) {
            if (x < horizontalLines[0].length && x >= 0 && y < horizontalLines.length && y >= 0) {
                if (!isProtected || horizontalLines[y][x] == Line.EMPTY) {
                    horizontalLines[y][x] = line;
                }
            }
        } else {
            if (x < verticalLines[0].length && x >= 0 && y < verticalLines.length && y >= 0) {
                if (!isProtected || verticalLines[y][x] == Line.EMPTY) {
                    verticalLines[y][x] = line;
                }
            }
        }
    }

    public void setLine(int x, int y, Direction direction, PointOrSquare pointOrSquare, Line line, boolean isProtected) {
        if (pointOrSquare == PointOrSquare.SQUARE) {
            if (direction == Direction.NORTH) {
                setLine(x + 1, y, true, line, isProtected);
            } else if (direction == Direction.EAST) {
                setLine(x + 1, y + 1, false, line, isProtected);
            } else if (direction == Direction.SOUTH) {
                setLine(x + 1, y + 1, true, line, isProtected);
            } else {
                setLine(x, y + 1, false, line, isProtected);
            }
        } else {
            if (direction == Direction.NORTH) {
                setLine(x, y, false, line, isProtected);
            } else if (direction == Direction.EAST) {
                setLine(x + 1, y, true, line, isProtected);
            } else if (direction == Direction.SOUTH) {
                setLine(x, y + 1, false, line, isProtected);
            } else {
                setLine(x, y, true, line, isProtected);
            }
        }
    }

    public void setLine(int x, int y, Direction direction, PointOrSquare pointOrSquare, Line line) {
        setLine(x, y, direction, pointOrSquare, line, true);
    }

    public void setLine(int x, int y, boolean isHorizontal, Line line) {
        setLine(x, y, isHorizontal, line, true);
    }

    public Number getNumber(int x, int y) {
        if (x >= sizeX || x < 0 || y >= sizeY || y <0) {
            return Number.EMPTY;
        } else {
            return numbers[y][x];
        }
    }

    public int getNumberValue(int x, int y) {
        return getNumber(x, y).getValue();
    }

    public void setNumber(int x, int y, Number number, boolean isProtected) {
        if (x < sizeX && x >=0 && y < sizeY && y >=0) {
            if (!isProtected || numbers[y][x] == Number.EMPTY) {
                numbers[y][x] = number;
            }
        }
    }

    public void setNumbers(int x, int y, int number, boolean isProtected) {
        setNumber(x, y, Number.getNumber(number), isProtected);
    }

    public void setNumbers(int x, int y, Number number) {
        setNumber(x, y, number, true);
    }

    public void setNumbers(int x, int y, int number) {
        setNumber(x, y, Number.getNumber(number), true);
    }

    public void setSlash(int x, int y, SlashTypes slashTypes, boolean isProtected) {
        if (x < slashes[0].length && x >= 0 && y < slashes.length && y >= 0) {
            if (!isProtected || slashes[y][x] == SlashTypes.EMPTY) {
                slashes[y][x] = slashTypes;
            }
        }
    }

    public void setSlash(int x, int y, DiagonalDirection diagonalDirection, PointOrSquare pointOrSquare, SlashTypes slashTypes, boolean isProtected) {
        if (pointOrSquare == PointOrSquare.SQUARE) {
            if (diagonalDirection == DiagonalDirection.NORTH_WEST) {
                setSlash(x * 2 + 1, y * 2 + 1, slashTypes, isProtected);
            } else if (diagonalDirection == DiagonalDirection.NORTH_EAST) {
                setSlash(x * 2 + 2, y * 2 + 1, slashTypes, isProtected);
            } else if (diagonalDirection == DiagonalDirection.SOUTH_EAST) {
                setSlash(x * 2 + 2, y * 2 + 2, slashTypes, isProtected);
            } else {
                setSlash(x * 2 + 1, y * 2 + 2, slashTypes, isProtected);
            }
        } else {
            if (diagonalDirection == DiagonalDirection.NORTH_WEST) {
                setSlash(x * 2, y * 2, slashTypes, isProtected);
            } else if (diagonalDirection == DiagonalDirection.NORTH_EAST) {
                setSlash(x * 2 + 1, y * 2, slashTypes, isProtected);
            } else if (diagonalDirection == DiagonalDirection.SOUTH_EAST) {
                setSlash(x * 2 + 1, y * 2 + 1, slashTypes, isProtected);
            } else {
                setSlash(x * 2, y * 2 + 1, slashTypes, isProtected);
            }
        }
    }

    public void setSlash(int x, int y, DiagonalDirection diagonalDirection, PointOrSquare pointOrSquare, SlashTypes slashTypes) {
        setSlash(x, y, diagonalDirection, pointOrSquare, slashTypes, true);
    }

    public SlashTypes getSlash(int x, int y) {
        if (x < slashes[0].length && x >= 0 && y < slashes.length && y >= 0) {
            return slashes[y][x];
        } else {
            return SlashTypes.EMPTY;
        }
    }

    public SlashTypes getSlash(int x, int y, DiagonalDirection diagonalDirection, PointOrSquare pointOrSquare) {
        if (pointOrSquare == PointOrSquare.SQUARE) {
            if (diagonalDirection == DiagonalDirection.NORTH_WEST) {
                return getSlash(x * 2 + 1, y * 2 + 1);
            } else if (diagonalDirection == DiagonalDirection.NORTH_EAST) {
                return getSlash(x * 2 + 2, y * 2 + 1);
            } else if (diagonalDirection == DiagonalDirection.SOUTH_EAST) {
                return getSlash(x * 2 + 2, y * 2 + 2);
            } else {
                return getSlash(x * 2 + 1, y * 2 + 2);
            }
        } else {
            if (diagonalDirection == DiagonalDirection.NORTH_WEST) {
                return getSlash(x * 2, y * 2);
            } else if (diagonalDirection == DiagonalDirection.NORTH_EAST) {
                return getSlash(x * 2 + 1, y * 2);
            } else if (diagonalDirection == DiagonalDirection.SOUTH_EAST) {
                return getSlash(x * 2 + 1, y * 2 + 1);
            } else {
                return getSlash(x * 2, y * 2 + 1);
            }
        }
    }

//    to string

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean withSlashes) {
        String string = "";

//        above the grid
//        line 1
        for (int x = 0; x < sizeX + 1; x++) {
            string += Line.EMPTY.getHorzStr();
            string += Line.X.getVertStr();
        }
        string += Line.EMPTY.getHorzStr();
        string += "\n";
//        line 2
        string += "    ";
        string += withSlashes ? SlashTypes.BOTH_OR_NEITHER.getString(DiagonalDirection.SOUTH_EAST) : " ";
        for (int x = 0; x < sizeX; x++) {
            string += " ";
            string += withSlashes ? getSlash(x, 0, DiagonalDirection.NORTH_EAST, PointOrSquare.POINT).getString(DiagonalDirection.SOUTH_WEST) : " ";
            string += "   ";
            string += withSlashes ? getSlash(x + 1, 0, DiagonalDirection.NORTH_WEST, PointOrSquare.POINT).getString(DiagonalDirection.SOUTH_EAST) : " ";
        }
        string += " ";
        string += withSlashes ? SlashTypes.BOTH_OR_NEITHER.getString(DiagonalDirection.SOUTH_WEST) : " ";
        string += "    ";
        string += "\n";

//        actual grid
        for (int y = 0; y < sizeY; y++) {
//            line 1
            string += Line.X.getHorzStr();
            for (int x = 0; x < sizeX; x++){
                string += "•";
                string += getLine(x, y, Direction.NORTH, PointOrSquare.SQUARE).getHorzStr();
            }
            string += "•";
            string += Line.X.getHorzStr();
            string += "\n";
//            line 2
            string += "    ";
            string += withSlashes ? getSlash(0, y, DiagonalDirection.SOUTH_WEST, PointOrSquare.POINT).getString(DiagonalDirection.NORTH_EAST) : " ";
            for (int x = 0; x < sizeX; x++){
                string += getLine(x, y, Direction.WEST, PointOrSquare.SQUARE).getVertStr(false);
                string += withSlashes ? getSlash(x, y, DiagonalDirection.NORTH_WEST, PointOrSquare.SQUARE).getString(DiagonalDirection.NORTH_WEST) : " ";
                string += "   ";
                string += withSlashes ? getSlash(x, y, DiagonalDirection.NORTH_EAST, PointOrSquare.SQUARE).getString(DiagonalDirection.NORTH_EAST) : " ";
            }
            string += getLine(sizeX - 1, y, Direction.EAST, PointOrSquare.SQUARE).getVertStr(false);
            string += withSlashes ? getSlash(sizeX, y, DiagonalDirection.SOUTH_EAST, PointOrSquare.POINT).getString(DiagonalDirection.NORTH_WEST) : " ";
            string += "    ";
            string += "\n";
//            line 3
            string += Line.EMPTY.getHorzStr();
            for (int x = 0; x < sizeX; x++){
                string += getLine(x, y, Direction.WEST, PointOrSquare.SQUARE).getVertStr(true);
                string += "  ";
                string += getNumber(x, y).getStr();
                string += "  ";
            }
            string += getLine(sizeX - 1, y, Direction.EAST, PointOrSquare.SQUARE).getVertStr(true);
            string += Line.EMPTY.getHorzStr();
            string += "\n";
//            line 4
            string += "    ";
            string += withSlashes ? getSlash(0, y + 1, DiagonalDirection.NORTH_WEST, PointOrSquare.POINT).getString(DiagonalDirection.SOUTH_EAST) : " ";
            for (int x = 0; x < sizeX; x++){
                string += getLine(x, y, Direction.WEST, PointOrSquare.SQUARE).getVertStr(false);
                string += withSlashes ? getSlash(x, y, DiagonalDirection.SOUTH_WEST, PointOrSquare.SQUARE).getString(DiagonalDirection.SOUTH_WEST) : " ";
                string += "   ";
                string += withSlashes ? getSlash(x, y, DiagonalDirection.SOUTH_EAST, PointOrSquare.SQUARE).getString(DiagonalDirection.SOUTH_EAST) : " ";
            }
            string += getLine(sizeX - 1, y, Direction.EAST, PointOrSquare.SQUARE).getVertStr(false);
            string += withSlashes ? getSlash(sizeX, y + 1, DiagonalDirection.NORTH_EAST, PointOrSquare.POINT).getString(DiagonalDirection.SOUTH_WEST) : " ";
            string += "    ";
            string += "\n";
        }

//        bottom line
        string += Line.X.getHorzStr();
        for (int x = 0; x < sizeX; x++){
            string += "•";
            string += getLine(x, sizeY - 1, Direction.SOUTH, PointOrSquare.SQUARE).getHorzStr();
        }
        string += "•";
        string += Line.X.getHorzStr();
        string += "\n";

//        below the grid
//        line 1
        string += "    ";
        string += withSlashes ? SlashTypes.BOTH_OR_NEITHER.getString(DiagonalDirection.NORTH_EAST) : " ";
        for (int x = 0; x < sizeX; x++) {
            string += " ";
            string += withSlashes ? getSlash(x, sizeY, DiagonalDirection.SOUTH_EAST, PointOrSquare.POINT).getString(DiagonalDirection.NORTH_WEST) : " ";
            string += "   ";
            string += withSlashes ? getSlash(x + 1, sizeY, DiagonalDirection.SOUTH_WEST, PointOrSquare.POINT).getString(DiagonalDirection.NORTH_EAST) : " ";
        }
        string += " ";
        string += withSlashes ? SlashTypes.BOTH_OR_NEITHER.getString(DiagonalDirection.NORTH_WEST) : " ";
        string += "    ";
        string += "\n";
//        line 2
        for (int x = 0; x < sizeX + 1; x++) {
            string += Line.EMPTY.getHorzStr();
            string += Line.X.getVertStr();
        }
        string += Line.EMPTY.getHorzStr();
        string += "\n";

        return string;
    }

//    load puzzle


}
