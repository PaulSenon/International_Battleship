package controler;

import model.Boat;
import tools.Coord;
import view.BattleShipGridGUI;
import view.BattleShipSquareGUI;
import view.GameGUIInterface;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.ActionType;

@objid ("648a3fb1-933c-4097-8cb6-e5b72780ae69")
public class BattleshipControlerClient implements BattleshipGameControlerModelView {
    @objid ("4209c7bb-9f52-40e1-b7aa-fe5edb441cdd")
    public BattleshipControlerClient() {
    }

    @objid ("62873f29-0bd5-4884-b3a4-4d9111923989")
    public void setGridPanel(BattleShipGridGUI gridGUI) {
		// TODO Auto-generated method stub

	}

    @Override
    public void setGUI(GameGUIInterface gameGUI) {

    }

    @objid ("532b238e-1f09-4ffa-9f1f-d21388e8d3f8")
    public void isPlayerOk() {
    }

    @Override
    public ActionType selectBoat(int x, int y) {
    	return null;
    }

    @Override
    public void moveBoat(int xDest, int yDest) {

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
		// TODO Auto-generated method stub

	}

	@Override
	public ActionType getCurrentAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shoot(BattleShipSquareGUI squareSelected) {
		// TODO Auto-generated method stub
	}

}