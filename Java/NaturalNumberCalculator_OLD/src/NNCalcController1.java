import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Put your name here
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        // TODO: fill in body
        NaturalNumber bottom = model.bottom();
        NaturalNumber top = model.top();

        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);
        if (top.compareTo(bottom) >= 0) {
            view.updateSubtractAllowed(true);
        } else {
            view.updateSubtractAllowed(false);
        }
        if (bottom.compareTo(new NaturalNumber2(2)) >= 0
                && bottom.compareTo(INT_LIMIT) <= 0) {
            view.updateRootAllowed(true);
        } else {
            view.updateRootAllowed(false);
        }
        if (bottom.compareTo(new NaturalNumber2(0)) >= 1) {
            view.updateDivideAllowed(true);
        } else {
            view.updateDivideAllowed(false);
        }

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = new NaturalNumber2(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */

        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        // TODO: fill in body
        NaturalNumber bottom = this.model.bottom();
        NaturalNumber top = this.model.top();
        NaturalNumber temp = new NaturalNumber2(bottom);
        top.transferFrom(temp);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        NaturalNumber newer = new NaturalNumber2(bottom);
        newer.add(top);
        bottom.transferFrom(newer);
        top.clear();
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        if (top.compareTo(bottom) >= 0) {
            NaturalNumber newer = new NaturalNumber2(top);
            newer.subtract(bottom);
            bottom.transferFrom(newer);
            top.clear();
        } else {
            top.clear();
            bottom.clear();
        }
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        bottom.multiply(top);
        top.clear();
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.divide(bottom);
        bottom.transferFrom(top);
        top.clear();
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        top.power(bottom.toInt());
        bottom.transferFrom(top);
        top.clear();
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processRootEvent() {

        // TODO: fill in body
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        int hold = top.toInt();
        bottom.root(hold);
        top.clear();
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        // TODO: fill in body
        NaturalNumber bottom = this.model.bottom();
        bottom.multiplyBy10(0);
        bottom.add(new NaturalNumber2(digit));
        updateViewToMatchModel(this.model, this.view);

    }

}
