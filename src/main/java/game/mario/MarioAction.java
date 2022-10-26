package game.mario;

/**
 * This interface describes all the action available to Mario in-game.
 * The fact that it is close to the number of buttons on the #Controller is just a coincidence... 😉
 */
public interface MarioAction {
    void moveLeft();
    void moveRight();
    void run();
    void jump();
    void crouch();
    void start();
}
