/**
 * {@code WaitingLine} with Secondary Methods
 *
 * @param <T>
 *            Type of waiting line of this
 * @mathdefinitions <pre>
 * IS_TOTAL_PREORDER (
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y, z: T
 *   ((r(x, y) or r(y, x))  and
 *    (if (r(x, y) and r(y, z)) then r(x, z)))
 *
 * IS_SORTED (
 *   s: string of T,
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y: T where (<x, y> is substring of s) (r(x, y))
 * </pre>
 */
public interface WaitingLine<T> extends WaitingLineKernel<T> {

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
    int customerLocation(T customer);

    /**
     * Removes {@code customer} from this
     *
     * @param customer
     *            {@code customer} to be removed from this
     * @requires <pre>
     *      {@code customer} is contained in this
     *      |#this| /= <>
     * </pre>
     * @updates this
     * @ensures #this = this / {@code customer}
     *
     */
    void removeFromLine(T customer);

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
    T replaceCustomer(int position, T newCustomer);

}
