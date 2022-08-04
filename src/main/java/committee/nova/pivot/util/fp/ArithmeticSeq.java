package committee.nova.pivot.util.fp;


public class ArithmeticSeq {
    private final int start;
    private final int end;

    private ArithmeticSeq(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static ArithmeticSeq to(int start, int end) {
        return new ArithmeticSeq(start, end);
    }

    public static ArithmeticSeq until(int start, int end) {
        return to(start, end + ((end > start) ? -1 : 1));
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

