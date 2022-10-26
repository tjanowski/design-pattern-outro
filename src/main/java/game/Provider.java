package game;

import console.Controller;
import game.Game;

public interface Provider {
    Game gameProvider();
    Controller controllerProvider();
}
