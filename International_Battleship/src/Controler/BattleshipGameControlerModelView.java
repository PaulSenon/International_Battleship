package Controler;

import tools.ActionType;
import View.GameGUIInterface;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d20af2ff-acab-4682-873f-cb742c29d82c")
public interface BattleshipGameControlerModelView extends BattleshipGameControlerModel {

    @objid ("54ce427a-8242-4cae-afb2-d0448d125757")
    void setGUI(GameGUIInterface gameGUI);

    @objid ("1a77cb4f-3543-44fb-a237-67fd9246c283")
    void isPlayerOk();

    ActionType selectBoat(int x, int y);

    void moveBoat(int xDest, int yDest);

}
