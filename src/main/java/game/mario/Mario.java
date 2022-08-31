package game.mario;

import console.ControllerEventSubscriber;
import game.Game;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

public class Mario implements Game { // TODO: Single responsibility design issue

    private final static List<String> GAME_DESIGN = Arrays.asList(
            "                                                     ",
            "                                   ▄                 ",
            "                                  ▇▇   ⚑       ⛫    ",
            "    ����         ���        ▇▇▇▇   ▏     ⛫⛫⛫   ",
            "___________🐢__║║__________║║__▇▇▇▇▇___╽____⛫⛫⛫⛫⛫_");


    // -------------------------------------------
    // Game state
    private volatile boolean isOnTheGround = true;
    private volatile boolean isAboveClear = true;
    private final Point currentMarioPosition = new Point(0, 0);
    // -------------------------------------------


    private final static int MAX_X_POSITION = GAME_DESIGN.stream().max(Comparator.comparingInt(String::length)).get().length();
    private final static int OFFSET = GAME_DESIGN.size() - 1;
    private final static String MARIO = "웃";

    private final LinkedBlockingDeque<Event> actions = new LinkedBlockingDeque<>();

    public Mario(ControllerEventSubscriber controller) { // TODO: Dependency inversion
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
            updateState();
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
                    if (isOnTheGround && isAboveClear) {
                        currentMarioPosition.y -= 3;
                    }
                }
            }
        } while(!isGameOver());

        System.out.println("GAME OVER " + "["+ getCurrentPosition() +"]");
    }

    private void updateState() {
        isOnTheGround = currentMarioPosition.y == 0  ;
        if (!isOnTheGround) {
            currentMarioPosition.y += 1;
        }
    }

    private void drawScreen() {
        List<String> screen = new ArrayList<>(GAME_DESIGN);
        String oldX = GAME_DESIGN.get(getCurrentRowIndex());
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
        return getCurrentPosition(0);
    }

    private String getCurrentPosition(int yOffset) {
        return substringUnicode(GAME_DESIGN.get(getCurrentRowIndex() - yOffset), currentMarioPosition.x);
    }

    private int getCurrentRowIndex() {
        int row = (currentMarioPosition.y + OFFSET) % (OFFSET + 1);
        return row;
    }

    private static String substringUnicode(String str, int idx) {
        return str.substring(idx, str.offsetByCodePoints(idx, 1));
    }

}
