package console;

public interface ControllerEventSubscriber {
    void onUpListener(Runnable up);
    void onDownListener(Runnable down);
    void onLeftListener(Runnable left);
    void onRightListener(Runnable right);
    void onStartListener(Runnable start);
    void onSelectListener(Runnable select);
    void onAListener(Runnable A);
    void onBListener(Runnable B);
}
