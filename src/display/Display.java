package display;

import puzzle.Direction;
import puzzle.Line;
import puzzle.PointOrSquare;
import puzzle.Puzzle;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel{
    private static final int scale = 100;
    private Puzzle puzzle;
    private boolean isPrettyVersion = true;

    public static void paintPuzzle(Puzzle puzzle, boolean isPrettyVersion) {
        JFrame frame = new JFrame("Slitherlink");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Display display = new Display();
        display.setValues(puzzle, isPrettyVersion);
        frame.add(display);

        frame.setSize(scale * (puzzle.getSizeX() + 2), scale * (puzzle.getSizeY() + 2));
        frame.setVisible(true);
    }

    private void setValues(Puzzle puzzle, boolean isPrettyVersion) {
        this.puzzle = puzzle;
        this.isPrettyVersion = isPrettyVersion;
    }

    private void setValues(Puzzle puzzle) {
        setValues(puzzle, true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintDots(g);
        paintNumbers(g, false);
        if (!isPrettyVersion) {
            paintNumbers(g, true);
        }
        paintVerticalLines(g);
        paintHorizontalLines(g);
    }

    private void paintDots(Graphics g) {
        g.setColor(Color.BLACK);
        int size = scale / 8;
        for (int yCoord = 0; yCoord < puzzle.getSizeY() + 1; yCoord++) {
            for (int xCoord = 0; xCoord < puzzle.getSizeX() + 1; xCoord++) {
                g.fillOval(xCoord * scale + scale / 2 - size / 2, yCoord * scale + scale / 2 - size / 2, size, size);
            }
        }
    }

    private void paintNumbers(Graphics g, boolean paintingTempNumbers) {
        g.setColor(Color.BLACK);
        if (paintingTempNumbers) {
            g.setFont(new Font("Arial", Font.ITALIC, scale / 4));
        } else {
            g.setFont(new Font("Arial", Font.PLAIN, scale / 2));
        }
        for (int yCoord = 0; yCoord < puzzle.getSizeY(); yCoord++) {
            for (int xCoord = 0; xCoord < puzzle.getSizeX(); xCoord++) {
                if (paintingTempNumbers && puzzle.getNumber(xCoord, yCoord, false) != puzzle.getNumber(xCoord, yCoord, true)) {
                    g.drawString(puzzle.getNumber(xCoord, yCoord, true).getStr(), xCoord * scale + scale * 15 / 16, yCoord * scale + scale * 11 / 10);
                } else if (!paintingTempNumbers) {
                    g.drawString(puzzle.getNumber(xCoord, yCoord, false).getStr(), xCoord * scale + scale * 8 / 9, yCoord * scale + scale * 7 / 6);
                }
            }
        }
    }

    private void paintVerticalLines(Graphics g) {
        g.setColor(Color.BLACK);

        Graphics2D g2d = (Graphics2D) g;
        Stroke thickerStroke = new BasicStroke(scale / 25); // Change the value for different thickness
        g2d.setStroke(thickerStroke);

        int xSize = scale / 15;

        for (int yCoord = 0; yCoord < puzzle.getSizeY(); yCoord++) {
            for (int xCoord = 0; xCoord < puzzle.getSizeX() + 1; xCoord++) {
                int x = xCoord * scale + scale / 2;
                int y = yCoord * scale + scale;

                if (puzzle.getLine(xCoord, yCoord, Direction.SOUTH, PointOrSquare.POINT) == Line.LINE) {
                    g.drawLine(x, y - scale / 2, x, y + scale / 2);
                } else if (puzzle.getLine(xCoord, yCoord, Direction.SOUTH, PointOrSquare.POINT) == Line.X) {
                    g.drawLine(x - xSize, y - xSize, x + xSize, y + xSize);
                    g.drawLine(x - xSize, y + xSize, x + xSize, y - xSize);
                }
            }
        }
    }

    private void paintHorizontalLines(Graphics g) {
        g.setColor(Color.BLACK);

        Graphics2D g2d = (Graphics2D) g;
        Stroke thickerStroke = new BasicStroke(scale / 25); // Change the value for different thickness
        g2d.setStroke(thickerStroke);

        int xSize = scale / 15;

        for (int yCoord = 0; yCoord < puzzle.getSizeY() + 1; yCoord++) {
            for (int xCoord = 0; xCoord < puzzle.getSizeX(); xCoord++) {
                int x = xCoord * scale + scale;
                int y = yCoord * scale + scale / 2;

                if (puzzle.getLine(xCoord, yCoord, Direction.EAST, PointOrSquare.POINT) == Line.LINE) {
                    g.drawLine(x - scale / 2, y, x + scale / 2, y);
                } else if (puzzle.getLine(xCoord, yCoord, Direction.EAST, PointOrSquare.POINT) == Line.X) {
                    g.drawLine(x - xSize, y - xSize, x + xSize, y + xSize);
                    g.drawLine(x - xSize, y + xSize, x + xSize, y - xSize);
                }
            }
        }
    }

}
