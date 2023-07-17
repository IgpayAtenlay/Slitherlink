public class Slash {

    private EitherOr eitherOr;

    public Slash() {
        this(EitherOr.EMPTY);
    }

    public Slash(EitherOr eitherOr) {
        this.eitherOr = eitherOr;
    }

    public void setProtectedEitherOr(EitherOr eitherOr) {
        if (this.eitherOr == EitherOr.EMPTY) {
            this.eitherOr = eitherOr;
        }
    }

    public void setEitherOr(EitherOr eitherOr) {
        this.eitherOr = eitherOr;
    }

    public EitherOr getEitherOr() {
        return eitherOr;
    }

}
