package controler;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.ActionType;

@objid ("d20af2ff-acab-4682-873f-cb742c29d82c")
public interface ControllerModelViewInterface extends ControllerModelInterface {
    void selectBoat(int x, int y);
    void moveBoat(int xDest, int yDest);
	void shoot(int x, int y);
    void rotateBoatClockWise();
    void rotateBoatCounterClockWise();
    void requestActioType(ActionType actionType);
}
