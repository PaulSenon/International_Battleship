package controler;


import tools.ActionType;
import tools.Coord;
import view.GridGUI;

public class ControllerClient implements ControllerModelViewInterface {

        public ControllerClient() {
    }

        public void setGridPanel(GridGUI gridGUI) {
		// TODO Auto-generated method stub

	}

        public void isPlayerOk() {
    }

    @Override
    public void selectBoat(int x, int y) {
		// TODO Auto-generated method stub

	}

    @Override
    public void moveBoat(int xDest, int yDest) {

    }

	@Override
	public void shoot(int x, int y) {

	}

    @Override
    public void rotateBoatClockWise() {

    }

    @Override
    public void rotateBoatCounterClockWise() {

    }

    @Override
    public void requestActionType(ActionType actionType) {

    }

	@Override
	public void specialAction(Coord coordSquare) {
		// TODO Auto-generated method stub

	}

	@Override
	public void EndActionsOfPlayer() {
		// TODO Auto-generated method stub
		
	}
}