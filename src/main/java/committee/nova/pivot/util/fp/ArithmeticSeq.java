package committee.nova.pivot.util.fp;

public record ArithmeticSeq(int start, int end) {
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

    public int getInterval() {
        return Math.abs(start - end);
    }

    public int getSize() {
        return getInterval() + 1;
    }

    public ArithmeticSeq reverse() {
        return new ArithmeticSeq(end, start);
    }

    public ArithmeticSeq offset(int lOffset, int rOffset) {
        return new ArithmeticSeq(start + lOffset, end + rOffset);
    }

    public ArithmeticSeq offset(int ofs) {
        return offset(ofs, ofs);
    }

    public ArithmeticSeq lOffset(int ofs) {
        return offset(ofs, 0);
    }

    public ArithmeticSeq rOffset(int ofs) {
        return offset(0, ofs);
    }
}
