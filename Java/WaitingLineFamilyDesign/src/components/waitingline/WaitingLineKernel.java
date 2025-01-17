package components.waitingline;

import components.standard.Standard;

/**
 *
 * @author Andrew Schneider(.911)
 * @author Eric Sullivan(.990)
 * @author cameron allen(.1915)
 * @author Yuxiang Luo(.929)
 * @author Ali Achour(.3)
 * @author Hitesh Bavisetti
 *
 */

/**
 * First-In-First-Out Waiting List component with primary methods.
 *
 * @author Andrew Schneider(.911)
 *
 * @param <T>
 *            type of {@code WaitingLineKernel}
 * @mathmodel type WaitingLineis modeled by string of T
 * @initially <pre>
* ():
*  ensures
*      this = <>
* </pre>
 * @iterator ~this.seen * ~this.unseen = this
 */
public interface WaitingLineKernel<T>
        extends Standard<WaitingLine<T>>, Iterable<T> {

    /**
     * Adds {@code customer} to the end {@code this} if it does not already
     * contain {@code customer}
     *
     * @param customer
     *            The entry to add to {@code this}
     * @aliases customer
     * @updates {@code this}
     * @requires <pre>
     *      {@code this} does not contain {@code x}
     * &#64;ensures
     *      this = #this * <customer>
     * </pre>
     *
     *
     */
    void appendLine(T customer);

    /**
     * Removes {@code customer} from front of waiting line {@code this}
     *
     * @param customer
     *            The {@code} customer to remove from {@code this}
     * @return Customer removed from front of waiting line {@code this}
     * @updates this
     *
     * @requires <pre>
     *          |this| /= <>
     * </pre>
     * @ensures #this = CustomerRemovedFromFront * this
     */
    T removeFirstInLine();

    /**
     * Reports the customer at the front of {@code this}
     *
     * @aliases Customer at front of line
     * @return Alias of customer at front of line
     * @requires <pre>
     *      |this| /= <>
     * </pre>
     * @ensures {@code <front> is prefix of this}
     */
    T customerAtFront();

    /**
     * Reports length of waiting line {@code this}
     *
     * @return length of {@code this}
     * @ensures {@code length} = |this|
     */
    int lengthOfLine();

}
