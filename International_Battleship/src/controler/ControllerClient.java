package controler;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.ActionType;
import view.GridGUI;

@objid ("648a3fb1-933c-4097-8cb6-e5b72780ae69")
public class ControllerClient implements ControllerModelViewInterface {

    @objid ("4209c7bb-9f52-40e1-b7aa-fe5edb441cdd")
    public ControllerClient() {
    }

    @objid ("62873f29-0bd5-4884-b3a4-4d9111923989")
    public void setGridPanel(GridGUI gridGUI) {
		// TODO Auto-generated method stub

	}

    @objid ("532b238e-1f09-4ffa-9f1f-d21388e8d3f8")
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
    public void requestActioType(ActionType actionType) {

    }
}