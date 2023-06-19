package game.mario;

import java.util.HashSet;
import java.util.Set;

/**
 * The purpose of #MarioController is to allow a subscriber to be informed of actions performed by Mario
 */
public class MarioController implements MarioAction, MarioEventSubscriber {
    private final Set<MarioEventListener> marioEventListeners = new HashSet<>();

    @Override
    public void subscribe(MarioEventListener marioEventListener) {
        marioEventListeners.add(marioEventListener);
    }

    @Override
    public void moveLeft() {
        notify(MarioEvent.MOVE_BACKWARD);
    }

    @Override
    public void moveRight() { notify(MarioEvent.MOVE_FORWARD); }

    @Override
    public void run() {
        notify(MarioEvent.RUN);
    }

    @Override
    public void jump() {
        notify(MarioEvent.JUMP);
    }

    @Override
    public void crouch() {
        notify(MarioEvent.CROUCH);
    }

    @Override
    public void start() {
        notify(MarioEvent.START);
    }

    private void notify(MarioEvent marioEvent) {
        marioEventListeners.forEach(marioEventListener -> marioEventListener.actionPerformed(marioEvent));
    }
}
