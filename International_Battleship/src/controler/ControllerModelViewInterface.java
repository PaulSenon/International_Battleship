package controler;


import tools.ActionType;

public interface ControllerModelViewInterface extends ControllerModelInterface {
    void selectBoat(int x, int y);
    void moveBoat(int xDest, int yDest);
	void shoot(int x, int y);
    void rotateBoatClockWise();
    void rotateBoatCounterClockWise();
    void requestActioType(ActionType actionType);
}
