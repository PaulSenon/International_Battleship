package Controler;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Model.Boat;
import Model.Coord;
import View.BattleShipGridGUI;

@objid ("876e9f9a-d77c-4a9d-809e-0854b8d95d55")
public class BattleShipControlerLocal implements BattleshipGameControlerModelView {
	
	//TODO private BattleshipGameModel battleShipGame;
	private BattleShipGridGUI gridGUI;
	public String currentAction;
	
	
    @objid ("be0371df-d97b-409c-a49c-c194011d27a8")
    public BattleShipControlerLocal(/*TODO BattleshipGameModel gameModel*/) {
    	//TODO this.battleShipGame = gameModel;
    	this.gridGUI = new BattleShipGridGUI();
    	this.currentAction = "None";
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

	@Override
	public void ActionWhenAction(Boat boat, Coord target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentAction(String actionChosen) {
		this.currentAction = actionChosen;
	}
	
	public String getCurrentAction() {
		return this.currentAction;
	}

}
