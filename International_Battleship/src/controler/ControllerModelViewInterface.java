package controler;


import tools.ActionType;

public interface ControllerModelViewInterface extends ControllerModelInterface {
    void selectBoat(int x, int y);
    void moveBoat(int xDest, int yDest);
	void shoot(int x, int y);
    void rotateBoatClockWise();
    void rotateBoatCounterClockWise();
    void requestActionType(ActionType actionType);
	void specialAction(int x, int y);
	void EndActionsOfPlayer();
//    void handleClick(int x, int y);
}
