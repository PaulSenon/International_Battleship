package controler;

import tools.ActionType;
import view.ButtonGUI;
import view.GameGUIInterface;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import view.SquareGUI;

@objid ("d20af2ff-acab-4682-873f-cb742c29d82c")
public interface ControllerModelViewInterface extends ControllerModelInterface {

    @objid ("54ce427a-8242-4cae-afb2-d0448d125757")
    void setGUI(GameGUIInterface gameGUI);

    @objid ("1a77cb4f-3543-44fb-a237-67fd9246c283")
    void isPlayerOk();

    ActionType selectBoat(int x, int y);

    void moveBoat(int xDest, int yDest);

	void setCurrentAction(ActionType action);

	ActionType getCurrentAction();

	void shoot(SquareGUI squareSelected);

    void changeButtonText(ButtonGUI button, String text);
}
