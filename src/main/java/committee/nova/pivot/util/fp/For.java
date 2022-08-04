package committee.nova.pivot.util.fp;

import java.util.function.IntConsumer;

public class For {
    private final ArithmeticSeq seq;

    public For(ArithmeticSeq seq) {
        this.seq = seq;
    }

    public static For create(ArithmeticSeq seq) {
        return new For(seq);
    }

    public void apply(IntConsumer c) {
        final int start = seq.getStart();
        final int end = seq.getEnd();
        if (start > end) {
            for (int i = start; i > end - 1; i--) {
                c.accept(i);
            }
            return;
        }
        for (int i = start; i < end + 1; i++) {
            c.accept(i);
        }
    }
}
