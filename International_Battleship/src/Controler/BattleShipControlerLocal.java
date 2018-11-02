package Controler;

import Model.BattleshipGameModel;
import View.BattleShipGridGUI;

import com.modeliosoft.modelio.javadesigner.annotations.objid;


@objid ("876e9f9a-d77c-4a9d-809e-0854b8d95d55")
public class BattleShipControlerLocal implements BattleshipGameControlerModelView {

    private BattleshipGameModel gameModel;
    private BattleShipGridGUI gridGUI;

    @objid ("be0371df-d97b-409c-a49c-c194011d27a8")
    public BattleShipControlerLocal(BattleshipGameModel gameModel) {
        this.gameModel = gameModel;
        this.gridGUI = new BattleShipGridGUI(); // set latter
    }

    @objid ("c21b247b-b822-4b13-9953-43f65175301b")
    public void setGridPanel(BattleShipGridGUI gridGUI) {
        this.gridGUI = gridGUI;
	}

    @objid ("4139c98d-c1d0-4cbd-8f28-29dd759f090f")
    public void isPlayerOk() {

    }

	@Override
	public void ActionWhenBoatIsSelectedOnGUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ActionWhenBoatIsMovedOnGUI() {
		// TODO Auto-generated method stub
		
	}

}
