package game.mario;

/**
 * This functional interface could be replaced by the Built-in #Consumer<MarioEvent> available natively in Java at the
 * cost of a lesser obvious intent
 */
@FunctionalInterface
interface MarioEventListener {
    void actionPerformed(MarioEvent a);
}
