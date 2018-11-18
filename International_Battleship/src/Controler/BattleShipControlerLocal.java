package Controler;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Model.BattleshipGameModel;
import Model.BattleshipModel;
import Model.Boat;
import Model.Coord;
import View.BattleShipButtonGUI;
import View.BattleShipButtonGUIListener;
import View.BattleShipGridGUI;
import View.BattleShipSquareGUI;
import View.BattleshipGUI;
import tools.ActionType;
import tools.ResultShoot;

@objid ("876e9f9a-d77c-4a9d-809e-0854b8d95d55")
public class BattleShipControlerLocal implements BattleshipGameControlerModelView {
	
	//TODO private BattleshipGameModel battleShipGame;
	private BattleShipGridGUI gridGUI;
	private ActionType actionType;
	private BattleshipModel battleShipGame;
	
    @objid ("be0371df-d97b-409c-a49c-c194011d27a8")
    public BattleShipControlerLocal(BattleshipModel gameModel) {
    	this.battleShipGame = gameModel;
    	this.gridGUI = new BattleShipGridGUI();
//		Uncomment the next line for normal use    	
//    	setCurrentAction(ActionType.SELECT);
//		This line is for test :
    	setCurrentAction(ActionType.MOVE);
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
	public void setCurrentAction(ActionType action) {
		this.actionType = action;
	}
	
	public ActionType getCurrentAction() {
		return this.actionType;
	}

	@Override
	public void shoot(BattleShipSquareGUI squareSelected) {
		Coord target = squareSelected.getCoord();
		this.gridGUI.messageToUser(this.battleShipGame.shoot(target));
//		BattleshipGUI.repaintAllButtons();
	}

}
