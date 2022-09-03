package game.zelda;

import game.Game;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Zelda implements Game {

    private final LinkedBlockingDeque<Runnable> actions = new LinkedBlockingDeque<>();

    public Zelda(EventSubscriber<Consumer<Runnable>> eventSubscriber) {
        eventSubscriber.subscribe(actions::add);
    }

    @Override
    public void play() {
        ExecutorService gameThread = Executors.newSingleThreadExecutor();
        gameThread.submit(this::firstLevel);
    }

    @SneakyThrows
    private void firstLevel() {
        System.out.println("=".repeat(20));
        System.out.println("    The Legend of   ");
        System.out.println("        Zelda       ");
        System.out.println("         ⚔️       ");
        System.out.println("=".repeat(20));
        System.out.println("  PUSH START BUTTON ");

        do {
            Runnable action = actions.poll(1, TimeUnit.SECONDS);
            action.run();
        } while (true);
    }

}
