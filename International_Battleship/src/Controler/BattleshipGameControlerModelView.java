package Controler;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import View.BattleShipGridGUI;
import View.BattleShipSquareGUI;
import tools.ActionType;
import tools.ResultShoot;

@objid ("d20af2ff-acab-4682-873f-cb742c29d82c")
public interface BattleshipGameControlerModelView extends BattleshipGameControlerModel {
    @objid ("54ce427a-8242-4cae-afb2-d0448d125757")
    void setGridPanel(BattleShipGridGUI gridGUI);

    @objid ("1a77cb4f-3543-44fb-a237-67fd9246c283")
    void isPlayerOk();

	void setCurrentAction(ActionType action);
	
	ActionType getCurrentAction();

	void shoot(BattleShipSquareGUI squareSelected);

}
