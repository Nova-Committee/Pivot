package committee.nova.pivot.util.fp;

import java.util.function.Supplier;

/**
 * @param <T> The type of the value to return
 * @author Tapio
 */
public class Try<T> {
    private final Supplier<T> sup;

    public Try(Supplier<T> sup) {
        this.sup = sup;
    }

    public T get() {
        return sup.get();
    }

    public T getOrElse(T defaultValue) {
        T v;
        try {
            v = get();
        } catch (Exception e) {
            v = defaultValue;
        }
        return v;
    }

    public T orElseGet(Supplier<T> defaultValueSup) {
        T v;
        try {
            v = get();
        } catch (Exception e) {
            v = defaultValueSup.get();
        }
        return v;
    }
}
