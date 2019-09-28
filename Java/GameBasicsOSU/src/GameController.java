/**
 * Controller interface.
 *
 * @author Bruce W. Weide
 *
 * @mathmodel <pre>
 * type NNCalcController is modeled by
 *   (model: NNCalcModel,
 *    view: NNCalcView)
 * </pre>
 * @initially <pre>
 * (NNCalcModel model, NNCalcView view):
 *   ensures
 *     this.model = model  and
 *     this.view = view
 * </pre>
 */
public interface GameController {

    /**
     * Processes event to clear bottom operand.
     *
     * @updates this.model.bottom, this.view
     * @ensures <pre>
     * this.model.bottom = 0  and
     * [this.view has been updated to match this.model]
     * </pre>
     */
    void moveCharacter();

}
