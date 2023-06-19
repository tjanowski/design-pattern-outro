package game.mario;

import game.Game;
import lombok.SneakyThrows;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Mario implements Game {

    private final static List<String> GAME_DESIGN = Arrays.asList(
            "                                                   ",
            "                                                   ",
            "                                  ▄                ",
            "                                 ▇▇   ⚑       ⛫    ",
            "    ����    ⚘   ���        ▇▇▇▇   ▏     ⛫⛫⛫   ",
            "___________🐢_║║__________║║__▇▇▇▇▇___╽___⛫⛫⛫⛫⛫_");

    private final static int MAX_X_POSITION = GAME_DESIGN.stream().max(Comparator.comparingInt(String::length)).get().length();
    private final static int OFFSET = GAME_DESIGN.size() - 1;
    private final static String MARIO = "웃";

    private final Point currentMarioPosition = new Point(0, 0);
    private final LinkedBlockingDeque<MarioEvent> actions = new LinkedBlockingDeque<>();

    public Mario(MarioEventSubscriber eventManager) {
        // Every MarioEvent triggered by the #MarioController will be added to an event queue
        eventManager.subscribe(actions::add);
    }

    @Override
    public void play() {
        ExecutorService gameThread = Executors.newSingleThreadExecutor();
        gameThread.submit(this::startGame);
    }

    @SneakyThrows
    public void startGame() {
        System.out.println("PRESS START");
        do {
            switch (actions.poll(1, TimeUnit.SECONDS)) {
                case MOVE_BACKWARD -> {
                    if (!isBlocked(MarioEvent.MOVE_BACKWARD)) {
                        currentMarioPosition.x -= 1;
                    }
                }
                case MOVE_FORWARD -> {
                    if (!isBlocked(MarioEvent.MOVE_FORWARD)) {
                        currentMarioPosition.x += 1;
                    }
                }
                case JUMP -> {
                    if (isOnTheGround() && isAboveClear()) {
                        currentMarioPosition.y -= 3;
                    }
                }
                default -> { continue; }
            }
            updateState();
            drawScreen();
        } while (!isGameOver());
        System.out.println("GAME OVER by " + "[" + getCurrentPosition() + "]");
    }

    private void updateState() {
        if (!isOnTheGround()) {
            currentMarioPosition.y += 1;
        }
    }

    private void drawScreen() {
        List<String> screen = new ArrayList<>(GAME_DESIGN);
        String oldX = GAME_DESIGN.get(getCurrentRowIndex());
        String newX = oldX.substring(0, currentMarioPosition.x) + MARIO + oldX.substring(currentMarioPosition.x + 1);
        screen.set(currentMarioPosition.y + OFFSET, newX);
        for (String horizontalLine : screen) {
            System.out.println(horizontalLine);
        }
    }

    private boolean isGameOver() {
        return switch (getCurrentPosition()) {
            case "🐢", "⚘", "╽", "⚑" -> true;
            default -> false;
        };
    }

    private String getCurrentPosition() {
        return getCurrentPosition(0, 0);
    }

    private String getCurrentPosition(int xOffset, int yOffset) {
        return substringUnicode(GAME_DESIGN.get(getCurrentRowIndex() - yOffset), currentMarioPosition.x + xOffset);
    }

    private int getCurrentRowIndex() {
        return (currentMarioPosition.y + OFFSET) % (OFFSET + 1);
    }

    private boolean isAboveClear() {
        return !getCurrentPosition(0, 1).equals("�");
    }

    private boolean isOnTheGround() {
        if (currentMarioPosition.y == 0) {
            return true;
        } else {
            String ground = getCurrentPosition(0, -1);
            return switch (ground) {
                case "�", "║", "▇" -> true;
                default -> false;
            };
        }
    }

    private boolean isBlocked(MarioEvent event) {
        int xOffset = 0;
        if (event.equals(MarioEvent.MOVE_BACKWARD) && currentMarioPosition.x > 0) {
            xOffset = -1;
        } else if (event.equals(MarioEvent.MOVE_FORWARD) && currentMarioPosition.x < MAX_X_POSITION) {
            xOffset = 1;
        } else {
            return false;
        }
        String facingBloc = getCurrentPosition(xOffset, 0);
        return switch (facingBloc) {
            case "�", "║", "▇" -> true;
            default -> false;
        };
    }

    private static String substringUnicode(String str, int idx) {
        return str.substring(idx, str.offsetByCodePoints(idx, 1));
    }
}
