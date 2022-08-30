package console;

public class NesController implements ControllerButton, ControllerEventSubscriber {

    private final static Runnable DO_NOTHING = () -> System.out.println("NO ACTION");

    private Runnable upAction = DO_NOTHING;
    private Runnable downAction = DO_NOTHING;
    private Runnable leftAction = DO_NOTHING;
    private Runnable rightAction = DO_NOTHING;
    private Runnable startAction = DO_NOTHING;
    private Runnable selectAction = DO_NOTHING;
    private Runnable AAction = DO_NOTHING;
    private Runnable BAction = DO_NOTHING;


    @Override
    public void up() {
        upAction.run();
    }

    @Override
    public void down() {
        downAction.run();
    }

    @Override
    public void left() {
        leftAction.run();
    }

    @Override
    public void right() {
        rightAction.run();
    }

    @Override
    public void start() {
        startAction.run();
    }

    @Override
    public void select() {
        selectAction.run();
    }

    @Override
    public void A() {
        AAction.run();
    }

    @Override
    public void B() {
        BAction.run();
    }


    @Override
    public void onUpListener(Runnable up) {
        this.upAction = up;
    }

    @Override
    public void onDownListener(Runnable down) {
        this.downAction = down;
    }

    @Override
    public void onLeftListener(Runnable left) {
        this.leftAction = left;
    }

    @Override
    public void onRightListener(Runnable right) {
        this.rightAction = right;
    }

    @Override
    public void onStartListener(Runnable start) {
        this.startAction = start;
    }

    @Override
    public void onSelectListener(Runnable select) {
        this.selectAction = select;
    }

    @Override
    public void onAListener(Runnable A) {
        this.AAction = A;
    }

    @Override
    public void onBListener(Runnable B) {
        this.BAction = B;
    }
}
