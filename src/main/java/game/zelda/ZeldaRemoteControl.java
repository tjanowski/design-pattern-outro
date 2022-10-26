package game.zelda;

import console.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZeldaRemoteControl implements Controller {

    private static final Logger LOG = LoggerFactory.getLogger(ZeldaRemoteControl.class);
    public static final String DO_NOTHING = "DO NOTHING";

    private final ZeldaAction zeldaAction;

    public ZeldaRemoteControl(ZeldaAction zeldaAction) {
        this.zeldaAction = zeldaAction;
    }

    @Override
    public void up() {
        zeldaAction.moveUp();
    }

    @Override
    public void down() {
        zeldaAction.moveDown();
    }

    @Override
    public void left() {
        zeldaAction.moveLeft();
    }

    @Override
    public void right() {
        zeldaAction.moveRight();
    }

    @Override
    public void start() {
        zeldaAction.start();
    }

    @Override
    public void select() {
        LOG.debug(DO_NOTHING);
    }

    @Override
    public void A() {
        zeldaAction.swingSword();
    }

    @Override
    public void B() {
        LOG.debug(DO_NOTHING);
    }
}
