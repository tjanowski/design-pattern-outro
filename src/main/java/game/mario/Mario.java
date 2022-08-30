package game.mario;

import console.ControllerEventSubscriber;
import game.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Mario implements Game {

    private final static List<String> GAME_DESIGN = Arrays.asList(
            "                                                   ",
            "                                 -                 ",
            "                                --   ⚑       ⛫     ",
            "    ����        ��   ⚘     ---   |      ⛫⛫⛫   ",
            "___________🐢_||________||___----___|____⛫⛫⛫⛫⛫_ ");


    // -------------------------------------------
    // Game state
    private volatile boolean isJumping = false;
    private final Point currentMarioPosition = new Point(0, 0);
    // -------------------------------------------


    private final static int MAX_X_POSITION = GAME_DESIGN.stream().max(Comparator.comparingInt(String::length)).get().length();
    private final static int OFFSET = 4;
    private final static String MARIO = "웃";

    private final LinkedBlockingDeque<Event> actions = new LinkedBlockingDeque<>();

    public Mario(ControllerEventSubscriber controller) {
        new MarioController(controller, actions);
    }


    @Override
    public void play() {
        ExecutorService gameThread = Executors.newSingleThreadExecutor();
        gameThread.submit(this::startGame);
    }

    public void startGame() {
//        do {
//            System.out.println("Press START to begin");
//        } while(eventQueue.poll().equals(Event.START));

        do {
            drawScreen();
            switch (actions.poll()) {
                case MOVE_BACKWARD -> {
                    if (currentMarioPosition.x > 0) {
                        currentMarioPosition.x -= 1;
                    }
                }
                case MOVE_FORWARD -> {
                    if (currentMarioPosition.x < MAX_X_POSITION) {
                        currentMarioPosition.x += 1;
                    }
                }
                case JUMP -> {
                    currentMarioPosition.y += 2;
                    isJumping = true;
                }
            }
        } while(!isGameOver());

        System.out.println("GAME OVER " + "["+ getCurrentPosition() +"]");
    }

    private void drawScreen() {
        List<String> screen = new ArrayList<>(GAME_DESIGN);

        String oldX = GAME_DESIGN.get(currentMarioPosition.y + OFFSET);
        String newX = oldX.substring(0,currentMarioPosition.x) + MARIO + oldX.substring(currentMarioPosition.x + 1);
        screen.set(currentMarioPosition.y + OFFSET, newX);

        for (String horizontalLine : screen) {
            System.out.println(horizontalLine);
        }
    }

    private boolean isGameOver() {
        switch (getCurrentPosition()) {
            case "🐢","⚘" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    private String getCurrentPosition() {
        return substringUnicode(GAME_DESIGN.get(currentMarioPosition.y + OFFSET), currentMarioPosition.x);
    }

    private static String substringUnicode(String str, int idx) {
        return str.substring(idx, str.offsetByCodePoints(idx, 1));
    }

}
