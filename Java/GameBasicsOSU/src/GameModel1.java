import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Model class.
 *
 * @author Put your name here
 */
public final class GameModel1 implements GameModel {

    /**
     * Model variables.
     */
    private final NaturalNumber top, bottom;

    /**
     * Default constructor.
     */
    public GameModel1() {
        this.top = new NaturalNumber2();
        this.bottom = new NaturalNumber2();
    }

    @Override
    public NaturalNumber top() {

        return this.top;
    }

    @Override
    public NaturalNumber bottom() {

        return this.bottom;
    }

}
