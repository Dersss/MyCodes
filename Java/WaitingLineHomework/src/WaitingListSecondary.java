import java.util.Iterator;

import components.queue.Queue;

public abstract class WaitingListSecondary<T> implements WaitingLine<T> {

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Queue<?>)) {
            return false;
        }
        Queue<?> q = (Queue<?>) obj;
        if (this.lengthOfLine() != q.length()) {
            return false;
        }
        Iterator<T> it1 = this.iterator();
        Iterator<?> it2 = q.iterator();
        while (it1.hasNext()) {
            T x1 = it1.next();
            Object x2 = it2.next();
            if (!x1.equals(x2)) {
                return false;
            }
        }
        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        final int samples = 2;
        final int a = 37;
        final int b = 17;
        int result = 0;
        /*
         * This code makes hashCode run in O(1) time. It works because of the
         * iterator order string specification, which guarantees that the (at
         * most) samples entries returned by the it.next() calls are the same
         * when the two Queues are equal.
         */
        int n = 0;
        Iterator<T> it = this.iterator();
        while (n < samples && it.hasNext()) {
            n++;
            T x = it.next();
            result = a * result + b * x.hashCode();
        }
        return result;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("<");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            if (it.hasNext()) {
                result.append(",");
            }
        }
        result.append(">");
        return result.toString();
    }

    /**
     * Finds the location of {@code customer} in this and returns it
     *
     * @param customer
     *            Customer to be located
     * @return location of {@code customer}
     * @requires <pre>
     *          |this| /= <>
     *          {@code customer} is in {@code this}
     *  </pre>
     *
     * @ensures {@code location} = position of customer in this
     */
    @Override
    public int customerLocation(T customer) {
        int length = this.lengthOfLine();
        int position = 0;
        boolean found = false;
        while (position < length && !found) {
            if (this.customerAtFront().equals(customer)) {
                found = true;
            } else {
                position++;
            }
            this.AppendLine(this.removeFirstInLine());
        }
        return position;

    }

    /**
     * Replaces and returns {@code customer} at {@code position} with
     * {@code newCutomer}
     *
     * @param position
     *            the location of the customer to be removed in this
     * @param newCustomer
     *            the {@code newCustomer} to be added at {@code position}
     * @aliases {@code customer} and {@code newCustomer}
     * @requires 0 <= {@code position} <= |this| |this| /= <>
     * @ensures this = #this[0, position) * <newCustomer> * #this[position + 1,
     *          |#this|) customer = #this[position, position + 1)
     */
    @Override
    public T replaceCustomer(int position, T newCustomer) {
        T removed = null;
        int index = 0;
        while (index < this.lengthOfLine()) {
            if (index == position) {
                removed = this.removeFirstInLine();
                this.AppendLine(newCustomer);
            }
            index++;
        }
        return removed;
    }
}
