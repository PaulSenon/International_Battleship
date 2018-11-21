package controler;

import model.BattleshipGameModel;
import model.Boat;
import view.BattleShipSquareGUI;
import view.BattleshipGUI;
import view.GameGUIInterface;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.ActionType;
import tools.Coord;
import tools.ProcessedPosition;

@objid ("876e9f9a-d77c-4a9d-809e-0854b8d95d55")
public class BattleShipControlerLocal implements BattleshipGameControlerModelView {

    private BattleshipGameModel gameModel;
    private GameGUIInterface gameGUI;
    private ActionType actionType;

    @objid ("be0371df-d97b-409c-a49c-c194011d27a8")
    public BattleShipControlerLocal(BattleshipGameModel gameModel) {
        this.gameModel = gameModel;
        this.gameGUI = new BattleshipGUI(this); // set latter
//		Uncomment the next line for normal use
//    	setCurrentAction(ActionType.SELECT);
//		This line is for test :
        setCurrentAction(ActionType.MOVE);
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
			System.out.println("YES boat has been selected");//pour debug mais je laisse tant que la vue n'est géré
		    this.gameGUI.setCurrentAction(ActionType.MOVE);//on change la valeur du ActionType pour la return après
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
        ProcessedPosition processedPosition = this.gameModel.rotateBoatClockWise();
        this.gameGUI.setBoatDirection(processedPosition);

    }

    public void rotateBoatCounterClockWise(){
        // TODO move direction dans tools
        ProcessedPosition processedPosition = this.gameModel.rotateBoatCounterClockWise();
        this.gameGUI.setBoatDirection(processedPosition);
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

            // TODO FIX#46 : CHANGE THIS CO COMMUNICATE WITH GLOBALE GUI INSTEAD OF GRIDGUI
            //this.gameGUI.messageToUser(this.battleShipGame.shoot(target));

//		BattleshipGUI.repaintAllButtons();
	}

}
