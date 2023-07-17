package console;

import game.Game;
import game.mario.*;
import game.zelda.ZeldaProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public Console console() {
        return new NES();
    }

    @Bean
    public NesController controller() {
        return new NesController();
    }
    @Bean
    MarioEventSubscriber marioEventSubscriber(ControllerCallback cc) {
        var mc = new MarioController();
        new ControllerMapper(cc, mc);
        return mc;
    }
    @Bean
    public Mario mario(MarioEventSubscriber eventManager) {
        return new Mario(eventManager);
    }
    @Bean
    public CommandLineRunner commandLineRunner(Console console, Controller controller1, Mario mario) {
        return args -> {
            console.plugController(controller1);

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
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
