public class Grid {

    private Point[][] pointGrid;
    private Square[][] squareGrid;

    public Grid() {
        this(20, 20);
    }

    public Grid(int sizeX, int sizeY) {
        this(new Point[sizeX+1][sizeY+1], new Square[sizeX][sizeY]);
    }

    public Grid(Point[][] pointGrid, Square[][] squareGrid) {
        this.pointGrid = pointGrid;
        this.squareGrid = squareGrid;
        init();
    }

    public void init() {
        for (int j = 0; j < pointGrid[0].length; j++) {
            for (int i = 0; i < pointGrid.length; i++) {
                makeSinglePoint(i,j);
            }
        }
        for (int j = 0; j < squareGrid[0].length; j++) {
            for (int i = 0; i < squareGrid.length; i++) {
                makeSingleSquare(i,j);
            }
        }
    }

    public void makeSinglePoint(int x, int y) {
        Line[] lines = new Line[4];
        if (x > 0) {
            lines[3] = pointGrid[x-1][y].getLine(Direction.EAST);
        } else {
            lines[3] = new Line();
        }
        if (y > 0) {
            lines[0] = pointGrid[x][y-1].getLine(Direction.SOUTH);
        } else {
            lines[0] = new Line();
        }
        lines[1] = new Line();
        lines[2] = new Line();
        pointGrid[x][y] = new Point(lines);
    }

    public void makeSingleSquare(int x, int y) {
        Line[] lines = new Line[4];
        Slash[] slashes = new Slash[4];

        lines[0] = pointGrid[x][y].getLine(Direction.EAST);
        lines[1] = pointGrid[x + 1][y + 1].getLine(Direction.NORTH);
        lines[2] = pointGrid[x + 1][y + 1].getLine(Direction.WEST);
        lines[3] = pointGrid[x][y].getLine(Direction.SOUTH);

        slashes[0] = pointGrid[x][y].getSlashes(DiagonalDirections.SOUTH_EAST);
        slashes[1] = pointGrid[x + 1][y].getSlashes(DiagonalDirections.SOUTH_WEST);
        slashes[2] = pointGrid[x + 1][y + 1].getSlashes(DiagonalDirections.NORTH_WEST);
        slashes[3] = pointGrid[x][y + 1].getSlashes(DiagonalDirections.NORTH_EAST);

        squareGrid[x][y] = new Square(lines, slashes);
    }

    public void ringOfX() {
        for (int i = 0; i < pointGrid.length; i++) {
            pointGrid[i][0].setLine(Direction.NORTH,LineOrX.X);
            pointGrid[i][pointGrid[0].length - 1].setLine(Direction.SOUTH,LineOrX.X);
        }
        for (int i = 0; i < pointGrid[0].length; i++) {
            pointGrid[0][i].setLine(Direction.WEST,LineOrX.X);
            pointGrid[pointGrid.length - 1][i].setLine(Direction.EAST,LineOrX.X);
        }
    }

    public Point[][] getPointGrid() {
        return pointGrid;
    }

    public Square[][] getSquareGrid() {
        return squareGrid;
    }

    public Number getNumber(int x, int y) {
        if (x >= 0 && x < squareGrid.length && y >= 0 && y < squareGrid[0].length) {
            return squareGrid[x][y].getNumber();
        } else {
            return Number.EMPTY;
        }
    }

    public String getNumberStr(int x, int y) {
        if (x >= 0 && x < squareGrid.length && y >= 0 && y < squareGrid[0].length) {
            return squareGrid[x][y].getNumberString();
        } else {
            return Number.EMPTY.getStr();
        }
    }

    public void setSquareLines(int x, int y, Direction direction, LineOrX lineOrX) {
        if (x >= 0 && x < squareGrid.length && y >= 0 && y < squareGrid[0].length) {
            squareGrid[x][y].setLine(direction, lineOrX);
        }
    }

    public void setSquareSlash(int x, int y, DiagonalDirections diagonalDirections, EitherOr eitherOr) {
        if (x >= 0 && x < squareGrid.length && y >= 0 && y < squareGrid[0].length) {
            squareGrid[x][y].setSlash(diagonalDirections, eitherOr);
        }
    }

    public EitherOr getPointSlash(int x, int y, DiagonalDirections diagonalDirections) {
        if (x >= 0 && x < pointGrid.length && y >= 0 && y < pointGrid[0].length) {
            return pointGrid[x][y].getSlashes(diagonalDirections).getEitherOr();
        } else {
            return EitherOr.EMPTY;
        }
    }

    public EitherOr getSquareSlash(int x, int y, DiagonalDirections diagonalDirections) {
        if (x >= 0 && x < squareGrid.length && y >= 0 && y < squareGrid[0].length) {
            return squareGrid[x][y].getSlash(diagonalDirections).getEitherOr();
        } else {
            return EitherOr.EMPTY;
        }
    }

    public void setPointLines(int x, int y, Direction direction, LineOrX lineOrX) {
        if (x >= 0 && x < pointGrid.length && y >= 0 && y < pointGrid[0].length) {
            pointGrid[x][y].setLine(direction, lineOrX);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int k = 1; k < 3; k++) {
            str += LineOrX.EMPTY.getHorzStr();
            for (int i = 0; i < pointGrid.length; i++) {
//                    lines
                if (pointGrid[i][0].getLine(Direction.NORTH).getLineOrX() == LineOrX.X && !(k == 1)) {
                    str += " ";
                } else {
                    str += pointGrid[i][0].getLine(Direction.NORTH).getLineOrX().getVertStr();
                }
//                    slashes
                if (k == 1) {
                    str += " ";
                } else if (k == 2) {
                    str += getPointSlash(i, 0, DiagonalDirections.NORTH_EAST).getNorthEast();
                }
                str += "   ";
//                    slashes
                if (k == 1) {
                    str += " ";
                } else if (k == 2) {
                    str += getPointSlash(i + 1, 0, DiagonalDirections.NORTH_WEST).getNorthWest();
                }
            }
            str += "\n";
        }
        for (int j = 0; j < pointGrid[0].length; j++) {
            str += pointGrid[0][j].getLine(Direction.WEST).getLineOrX().getHorzStr();
            for (int i = 0; i < pointGrid.length; i++) {
                str += "•";
                str += pointGrid[i][j].getLine(Direction.EAST).getLineOrX().getHorzStr();
            }
            str += "\n";

            for (int k = 0; k < 3; k++) {
                str += LineOrX.EMPTY.getHorzStr();
                for (int i = 0; i < pointGrid.length; i++) {
//                    lines
                    if (pointGrid[i][j].getLine(Direction.SOUTH).getLineOrX() == LineOrX.X && !(k == 1)) {
                        str += " ";
                    } else {
                        str += pointGrid[i][j].getLine(Direction.SOUTH).getLineOrX().getVertStr();
                    }
//                    slashes
                    if (k == 0) {
                        str += getSquareSlash(i, j, DiagonalDirections.NORTH_WEST).getNorthWest();
                    } else if (k == 1) {
                        str += " ";
                    } else if (k == 2) {
                        str += getSquareSlash(i, j, DiagonalDirections.SOUTH_WEST).getSouthWest();
                    }
                    str += " ";
//                    number
                    if (k == 1) {
                        str += getNumberStr(i, j);
                    } else {
                        str += " ";
                    }
                    str += " ";
//                    slashes
                    if (k == 0) {
                        str += getSquareSlash(i, j, DiagonalDirections.NORTH_EAST).getNorthEast();
                    } else if (k == 1) {
                        str += " ";
                    } else if (k == 2) {
                        str += getSquareSlash(i, j, DiagonalDirections.SOUTH_EAST).getSouthEast();
                    }
                }
                str += "\n";
            }
        }
        return str;
    }

}
