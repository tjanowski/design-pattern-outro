package game.zelda;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.function.Consumer;

public class ZeldaActionImpl implements ZeldaAction, EventSubscriber<Consumer<Runnable>> {

    private final Set<Consumer<Runnable>> zeldaEventListeners = Sets.newConcurrentHashSet();
    private volatile boolean pause = false;

    @Override
    public void moveLeft() {
        notify(() -> System.out.println("\uD83E\uDDDD going ⬅️"));
    }

    @Override
    public void moveRight() {
        notify(() -> System.out.println("\uD83E\uDDDD going ➡️"));

    }

    @Override
    public void moveUp() {
        notify(() -> System.out.println("\uD83E\uDDDD going ⬆️"));
    }

    @Override
    public void moveDown() {
        notify(() -> System.out.println("\uD83E\uDDDD going ⬇️"));
    }

    @Override
    public void swingSword() {
        notify(() -> System.out.println("\uD83E\uDDDD swinging \uD83D\uDDE1️"));
    }

    @Override
    public void start() {
        if (pause) {
            pause = false;
            notify(() -> System.out.println("RESUMING GAME..."));
        } else {
            pause = true;
            notify(() -> {
                System.out.println("PAUSE");
                System.out.println("PRESS START TO CONTINUE");
            });
        }
    }
    private void notify(Runnable action) {
        zeldaEventListeners.forEach(runnableConsumer -> runnableConsumer.accept(action));
    }

    @Override
    public void subscribe(Consumer<Runnable> eventListener) {
        zeldaEventListeners.add(eventListener);
    }

}
