import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> removed = this.newInstance();
        for (T x : s) {
            if (this.contains(x)) {
                T x2 = this.remove(x);
                removed.add(x2);
            }
        }
        return removed;
    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> hold = s.newInstance();
        while (s.size() > 0) {
            T x = s.removeAny();
            if (!this.contains(x)) {
                this.add(x);
            } else {
                hold.add(x);
            }
        }
        s.transferFrom(hold);
    }

}
