package game.zelda;

interface EventSubscriber<T> {
    void subscribe(T eventListener);
}
