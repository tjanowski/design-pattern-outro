package console;

import game.Game;

public interface Console {

    void load(Game game);

    void unload();

    void powerOn();

    void powerOff();

    void plugController(Controller controller);

    void unplugController();
}
