/**
 * Model interface.
 *
 * The Natural Number Calculator model consists of the top and bottom operands
 * (which have only getter methods).
 *
 * @author Bruce W. Weide
 *
 * @mathmodel <pre>
 * type NNCalcModel is modeled by
 *   (top: NATURAL_NUMBER,
 *    bottom: NATURAL_NUMBER)
 * </pre>
 * @initially <pre>
 * ():
 *  ensures
 *   this = (0, 0)
 * </pre>
 */
public interface GameModel {

}
