package console;

public interface ControllerCallback {
    void onUp(ButtonCallback up);
    void onDown(ButtonCallback down);
    void onLeft(ButtonCallback left);
    void onRight(ButtonCallback right);
    void onStart(ButtonCallback start);
    void onSelect(ButtonCallback select);
    void onA(ButtonCallback A);
    void onB(ButtonCallback B);
}
