import components.stack.Stack;

/**
 * Controller class.
 *
 * @author Bruce W. Weide
 * @author Paolo Bucci
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        Stack<String> temp = model.output().newInstance();
        String output = "";

        while (model.output().length() > 0) {
            String hold = model.output().pop();
            output = output + hold;
            temp.push(hold);
        }
        model.output().transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        view.updateInputDisplay(input);
        view.updateOutputDisplay(output);
    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes reset event.
     */
    @Override
    public void processResetEvent() {
        /*
         * Update model in response to this event
         */
        this.model.setInput("");
        this.model.output().clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAppendEvent(String input) {
        // TODO Auto-generated method stub
        this.model.setInput("");
        this.model.output().push(input);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processUndoEvent() {
        // TODO Auto-generated method stub

        String x = this.model.output().pop();
        this.model.setInput(x);
        updateViewToMatchModel(this.model, this.view);
    }

}
