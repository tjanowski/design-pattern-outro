package console;

import game.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Stack;

public class NES implements Console {
    private static final Logger LOG = LoggerFactory.getLogger(NES.class);
    private static final int MAX_CONTROLLER_SLOT = 2;

    private final Stack<Controller> controllers = new Stack<>();
    private Game game;

    @Override
    public void load(Game game) {
        this.game = game;
    }

    @Override
    public void unload() {
        this.game = null;
    }

    @Override
    public void powerOn() {
        if (Objects.nonNull(game)) {
            game.play();
        } else {
            LOG.error("No game in console");
        }
    }

    @Override
    public void powerOff() {
        LOG.info("Switching off");
    }

    @Override
    public void plugController(Controller controller) {
        if (controllers.size() < MAX_CONTROLLER_SLOT) {
            controllers.push(controller);
        } else {
            LOG.warn("NES has only {} slots", MAX_CONTROLLER_SLOT);
        }
    }

    @Override
    public void unplugController() {
        if (controllers.size() > 0) {
            controllers.pop();
        } else {
            LOG.warn("No controller plugged");
        }
    }

}
