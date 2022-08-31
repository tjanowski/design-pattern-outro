package console;

import game.mario.Mario;

public class NES {

    public static void main(String[] args) {

        NesController controller = new NesController();
        Mario marioGame = new Mario(controller);
        marioGame.play();

        controller.right();
        controller.right();
        controller.right();


        controller.left();
        controller.left();
        controller.left();

        controller.right();
        controller.right();
        controller.right();


        controller.A();
        controller.left();
        controller.left();
        controller.left();

    }

}
