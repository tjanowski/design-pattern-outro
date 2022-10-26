package console;

import com.google.inject.*;
import game.Game;
import game.mario.*;
import game.zelda.ZeldaProvider;

public class Application extends AbstractModule {

    /**
     * Guice is a DI framework created by Google
     * it injects implementation in-place of interfaces the same way as Spring beans work
     * The line bellow shows that the #Console interface will be injected by the #NES implementation whenever encountered.
     */
    @Override
    protected void configure() {
        bind(Console.class).to(NES.class);

        bind(NesController.class).in(Singleton.class);
        bind(ControllerCallback.class).to(NesController.class);
        bind(Controller.class).to(NesController.class);

        bind(MarioAction.class).to(MarioController.class);
        bind(MarioEventSubscriber.class).to(MarioController.class);

        bind(ControllerMapper.class).asEagerSingleton();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Application());

        NES console = new NES();

        // Using Guice to retrieve the #NesController implementation of the #Controller interface
        Controller controller1 = injector.getInstance(NesController.class);
        console.plugController(controller1);

        // Using Guice to retrieve the #Mario implementation of the #Game interface
        // injected with an implementation for #MarioEventSubscriber
        Game mario = injector.getInstance(Mario.class);
        console.load(mario);
        console.powerOn();

        controller1.right();
        controller1.right();
        controller1.right();

        controller1.left();
        controller1.left();
        controller1.left();

        controller1.right();
        controller1.right();
        controller1.right();

        controller1.B();
        controller1.right();
        controller1.right();

        controller1.right();
        controller1.right();
        controller1.right();
        controller1.right();
        controller1.right();
        controller1.right();

        console.powerOff();
        console.unload();

        // Now playing Zelda
        ZeldaProvider zeldaProvider = new ZeldaProvider();

        Game zelda = zeldaProvider.gameProvider();
        console.load(zelda);
        console.powerOn();

        Controller controller = zeldaProvider.controllerProvider();
        controller.right();
        controller.up();
        controller.left();
        controller.down();
        controller.A();
        controller.B();
        controller.start();
        controller.start();
        controller.A();
    }
}
