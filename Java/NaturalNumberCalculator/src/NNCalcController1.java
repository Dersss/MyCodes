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
        /**
         * NN variables bottom and top hold model values of corresponding text
         * areas. Then updates view top and bottom displays to match the model.
         */
        NaturalNumber bottom = model.bottom();
        NaturalNumber top = model.top();
        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);

        /**
         * Conditions for operations that are prohibited: if top is less than
         * bottom, subtraction not allowed if bottom is less than 2 or greater
         * than integer limit root not allowed if bottom equals zero, divide not
         * allowed
         */
        if (top.compareTo(bottom) < 0) {
            view.updateSubtractAllowed(false);
        } else {
            view.updateSubtractAllowed(true);
        }
        if (bottom.compareTo(TWO) >= 0 && bottom.compareTo(INT_LIMIT) <= 0) {
            view.updateRootAllowed(true);
        } else {
            view.updateRootAllowed(false);
        }
        if (bottom.compareTo(new NaturalNumber2(0)) > 0) {
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
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        /**
         * Get aliases to top and bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        NaturalNumber top = this.model.top();

        /**
         * Update model in response to this event
         */
        NaturalNumber temp = new NaturalNumber2(bottom);
        top.transferFrom(temp);

        /**
         * Update view to reflect change in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {

        /**
         * Process add event and change model to reflect operation
         */
        this.model.bottom().add(this.model.top());
        this.model.top().clear();

        /**
         * Update view to reflect change in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {

        /**
         * Process subtract event and change model to reflect operation
         */
        this.model.bottom().subtract(this.model.top());
        this.model.top().clear();

        /**
         * Update view to reflect change in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processMultiplyEvent() {

        /**
         * Process multiply event and change model to reflect operation
         */
        this.model.bottom().multiply(this.model.top());
        this.model.top().clear();

        /**
         * Update view to reflect change in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {

        /**
         * Process divide event and change model to reflect operation
         */
        this.model.top().divide(this.model.bottom());
        this.model.bottom().transferFrom(this.model.top());
        this.model.top().clear();

        /**
         * Update view to reflect change in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processPowerEvent() {

        /**
         * Process power event and change model to reflect operation
         */
        this.model.bottom().power(this.model.top().toInt());
        this.model.top().clear();

        /**
         * Update view to reflect change in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        /**
         * Process root event and change model to reflect operation
         */
        this.model.bottom().root(this.model.top().toInt());
        this.model.top().clear();

        /**
         * Update view to reflect change in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        /**
         * Process add new digit event and change model to reflect operation
         */
        this.model.bottom().multiplyBy10(0);
        this.model.bottom().add(new NaturalNumber2(digit));

        /**
         * Update view to reflect change in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

}
