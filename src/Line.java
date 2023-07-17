public class Line {
    private LineOrX lineOrX;

    public Line() {
        this(LineOrX.EMPTY);
    }

    public Line(LineOrX lineOrX) {
        this.lineOrX = lineOrX;
    }

    public void setProtectedLineOrX(LineOrX lineOrX) {
        if (this.lineOrX == LineOrX.EMPTY) {
            this.lineOrX = lineOrX;
        }
    }

    public void setLineOrX(LineOrX lineOrX) {
        this.lineOrX = lineOrX;
    }

    public LineOrX getLineOrX() {
        return lineOrX;
    }
}
