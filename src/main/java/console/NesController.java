package console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class allow to bind a physical button on the #Controller to an arbitrary callback
 */
public class NesController implements Controller, ControllerCallback {

    private static final Logger LOG = LoggerFactory.getLogger(NesController.class);

    private final static ButtonCallback DO_NOTHING = () -> LOG.debug("NO ACTION");

    private ButtonCallback upAction = DO_NOTHING;
    private ButtonCallback downAction = DO_NOTHING;
    private ButtonCallback leftAction = DO_NOTHING;
    private ButtonCallback rightAction = DO_NOTHING;
    private ButtonCallback startAction = DO_NOTHING;
    private ButtonCallback selectAction = DO_NOTHING;
    private ButtonCallback AAction = DO_NOTHING;
    private ButtonCallback BAction = DO_NOTHING;

    @Override
    public void up() {
        upAction.execute();
    }
    @Override
    public void down() {
        downAction.execute();
    }
    @Override
    public void left() {
        leftAction.execute();
    }
    @Override
    public void right() { rightAction.execute(); }
    @Override
    public void start() {
        startAction.execute();
    }
    @Override
    public void select() {
        selectAction.execute();
    }
    @Override
    public void A() {
        AAction.execute();
    }
    @Override
    public void B() {
        BAction.execute();
    }
    @Override
    public void onUp(ButtonCallback up) {
        this.upAction = up;
    }
    @Override
    public void onDown(ButtonCallback down) {
        this.downAction = down;
    }
    @Override
    public void onLeft(ButtonCallback left) { this.leftAction = left; }
    @Override
    public void onRight(ButtonCallback right) { this.rightAction = right; }
    @Override
    public void onStart(ButtonCallback start) {
        this.startAction = start;
    }
    @Override
    public void onSelect(ButtonCallback select) {
        this.selectAction = select;
    }
    @Override
    public void onA(ButtonCallback A) {
        this.AAction = A;
    }
    @Override
    public void onB(ButtonCallback B) {
        this.BAction = B;
    }
}
