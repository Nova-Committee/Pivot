package committee.nova.pivot.util.fp;

import org.jetbrains.annotations.NotNull;

import java.util.function.IntConsumer;

public class For {
    private final ArithmeticSeq seq;

    private For(ArithmeticSeq seq) {
        this.seq = seq;
    }

    /**
     * @param seq The arithmetic sequence to be used
     * @return A new "For" of the arithmetic sequence in
     */
    public static @NotNull For in(ArithmeticSeq seq) {
        return new For(seq);
    }

    /**
     * A convenient substitute for "For.in(ArithmeticSeq.to(a, b))"
     *
     * @param start The start number of the arithmetic sequence
     * @param end   The end number of the arithmetic sequence
     * @return A new "For" of a corresponding arithmetic sequence
     */
    public static @NotNull For to(int start, int end) {
        return in(ArithmeticSeq.to(start, end));
    }

    /**
     * A convenient substitute for "For.in(ArithmeticSeq.until(a, b))"
     *
     * @param start The start number of the arithmetic sequence
     * @param end   The end number of the arithmetic sequence(not included in)
     * @return A new "For" of a corresponding arithmetic sequence
     */
    public static @NotNull For until(int start, int end) {
        return in(ArithmeticSeq.until(start, end));
    }

    /**
     * Run and pass the index as an argument to the function
     *
     * @param func The function to be applied
     */
    public void exploit(IntConsumer func) {
        final int start = seq.getStart();
        final int end = seq.getEnd();
        if (start > end) {
            for (int i = start; i >= end; i--) {
                func.accept(i);
            }
            return;
        }
        for (int i = start; i <= end; i++) {
            func.accept(i);
        }
    }

    /**
     * Simply run the function multiple times
     *
     * @param func The function to run
     */
    public void run(Runnable func) {
        final int size = seq.getSize();
        for (int i = 0; i < size; i++) func.run();
    }
}
