package game.mario;

import console.ControllerCallback;

/**
 * Link a generic button controller callback to a specific action performed by Mario
 */
public class ControllerMapper {
    public ControllerMapper(ControllerCallback controllerCallback, MarioAction marioAction) {
        controllerCallback.onLeft(marioAction::moveLeft);
        controllerCallback.onRight(marioAction::moveRight);
        controllerCallback.onDown(marioAction::crouch);
        controllerCallback.onA(marioAction::run);
        controllerCallback.onB(marioAction::jump);
        controllerCallback.onStart(marioAction::start);
    }
}