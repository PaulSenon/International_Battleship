package Controler;

import Model.BattleshipGameModel;
import tools.Coord;
import View.BattleshipGUI;

import View.GameGUIInterface;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.ActionType;
import tools.Direction;


@objid ("876e9f9a-d77c-4a9d-809e-0854b8d95d55")
public class BattleShipControlerLocal implements BattleshipGameControlerModelView {

    private BattleshipGameModel gameModel;
    private GameGUIInterface gameGUI;

    @objid ("be0371df-d97b-409c-a49c-c194011d27a8")
    public BattleShipControlerLocal(BattleshipGameModel gameModel) {
        this.gameModel = gameModel;
        this.gameGUI = new BattleshipGUI(this); // set latter
    }

    @objid ("c21b247b-b822-4b13-9953-43f65175301b")
    public void setGUI(GameGUIInterface gameGUI) {
        this.gameGUI = gameGUI;
	}


    @objid ("4139c98d-c1d0-4cbd-8f28-29dd759f090f")
    public void isPlayerOk() {
        // TODO Auto-generated method stub
    }

	public ActionType selectBoat(int x, int y) {
		if(this.gameModel.selectBoat(x, y)){
		    this.gameGUI.setCurrentAction(ActionType.MOVE);
        }
		return this.gameGUI.getCurrentAction();
		
	}

    public void moveBoat(int xDest, int yDest){
        Coord coord = this.gameModel.moveBoat(xDest, yDest);
        this.gameGUI.setBoatPos(coord);
        this.gameGUI.setCurrentAction(ActionType.SELECT);
    }

    public void rotateBoatClockWise(){
        // TODO move direction dans tools
        Direction direction = this.gameModel.rotateBoatClockWise();
        this.gameGUI.setBoatDirection(direction);

    }

    public void rotateBoatCounterClockWise(){
        // TODO move direction dans tools
        Direction direction = this.gameModel.rotateBoatCounterClockWise();
        this.gameGUI.setBoatDirection(direction);
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
