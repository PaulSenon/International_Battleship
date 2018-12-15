package controler;


import tools.ActionType;
import tools.Coord;

public interface ControllerModelViewInterface extends ControllerModelInterface {
    void selectBoat(int x, int y);
    void moveBoat(int xDest, int yDest);
	void shoot(int x, int y);
    void rotateBoatClockWise();
    void rotateBoatCounterClockWise();
    void requestActionType(ActionType actionType);
	void specialAction(Coord coordSquare);
	void EndActionsOfPlayer();
}
