package game.zelda;

import console.ControllerEventSubscriber;
import game.Game;

public class Zelda implements Game {


    private ControllerEventSubscriber controller;

    public Zelda(ControllerEventSubscriber controller) {
        this.controller = controller;
    }


    @Override
    public void play() {

    }
}
