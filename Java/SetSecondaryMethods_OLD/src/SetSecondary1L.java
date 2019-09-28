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

    /**
     * @param s
     *
     * @return this.elements removed
     *
     * @updates this
     *
     * @ensures this = #this \ s
     * @ensures remove = #this intersection s
     *
     */
    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body

    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body

    }

}
