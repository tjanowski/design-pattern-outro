package game.zelda;

import console.Controller;
import game.Game;
import game.Provider;

public class ZeldaProvider implements Provider {
    private final ZeldaActionImpl zeldaAction;

    public ZeldaProvider() {
        zeldaAction = new ZeldaActionImpl();
    }

    @Override
    public Game gameProvider() {
        return new Zelda(zeldaAction);
    }

    @Override
    public Controller controllerProvider() {
        return new ZeldaRemoteControl(zeldaAction);
    }
}
