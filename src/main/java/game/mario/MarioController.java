package game.mario;

import console.ControllerEventSubscriber;

import java.util.Queue;

public class MarioController {


    private final Queue<Event> sharedEventsQueue;

    public MarioController(ControllerEventSubscriber eventSubscriber, Queue<Event> sharedEventsQueue) {

        eventSubscriber.onUpListener(() -> System.out.println("Not used"));
        eventSubscriber.onUpListener(this::Crouch);
        eventSubscriber.onLeftListener(this::MoveBackward);
        eventSubscriber.onRightListener(this::MoveForward);
        eventSubscriber.onStartListener(this::Start);
        eventSubscriber.onSelectListener(this::Pause);
        eventSubscriber.onAListener(this::Jump);
        eventSubscriber.onBListener(this::Run);

        this.sharedEventsQueue = sharedEventsQueue;
    }

    public void Crouch() {
        sharedEventsQueue.add(Event.CROUCH);
    }

    public void MoveBackward() {
        sharedEventsQueue.add(Event.MOVE_BACKWARD);
    }

    public void MoveForward() {
        sharedEventsQueue.add(Event.MOVE_FORWARD);
    }

    public void Start() {
        sharedEventsQueue.add(Event.START);
    }

    public void Pause() {
        sharedEventsQueue.add(Event.PAUSE);
    }

    public void Jump() {
        sharedEventsQueue.add(Event.JUMP);
    }

    public void Run() {
        sharedEventsQueue.add(Event.RUN);
    }
}
