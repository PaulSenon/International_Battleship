package controler;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import model.GameModelInterface;
import tools.ActionType;
import tools.ProcessedPosition;
import view.GameGUIInterface;

@objid ("876e9f9a-d77c-4a9d-809e-0854b8d95d55")
public class ControllerLocal implements ControllerModelViewInterface {

    private GameModelInterface gameModel;
    private GameGUIInterface gameGUI;

    @objid ("be0371df-d97b-409c-a49c-c194011d27a8")
    public ControllerLocal(GameModelInterface gameModel, GameGUIInterface gameGUI) {
        this.gameModel = gameModel;
        this.gameGUI = gameGUI; // set latter
    }

	public void selectBoat(int x, int y) {
		if(this.gameModel.selectBoat(x, y)){
			System.out.println("YES boat has been selected");//pour debug mais je laisse tant que la vue n'est géré
		    this.gameGUI.setCurrentAction(ActionType.MOVE);//on change la valeur du ActionType pour la return après
            this.gameGUI.setSelectedBoatByCoord(x,y);
        }
	}

    public void moveBoat(int xDest, int yDest){
        ProcessedPosition processedPosition = this.gameModel.moveBoat(xDest, yDest);
        this.gameGUI.setProcessedPotion(processedPosition);
        this.gameGUI.setCurrentAction(ActionType.SELECT);
    }

    public void rotateBoatClockWise(){
        // TODO move direction dans tools
        ProcessedPosition processedPosition = this.gameModel.rotateBoatClockWise();
        this.gameGUI.setProcessedPotion(processedPosition);
        this.gameGUI.setCurrentAction(ActionType.SELECT);
    }

    public void rotateBoatCounterClockWise(){
        // TODO move direction dans tools
        ProcessedPosition processedPosition = this.gameModel.rotateBoatCounterClockWise();
        this.gameGUI.setProcessedPotion(processedPosition);
        this.gameGUI.setCurrentAction(ActionType.SELECT);

    }

	@Override
	public void shoot(int x, int y) {
//		Coord target = squareSelected.getCoord();

            // TODO FIX#46 : CHANGE THIS CO COMMUNICATE WITH GLOBALE GUI INSTEAD OF GRIDGUI
            //this.gameGUI.messageToUser(this.battleShipGame.shoot(target));

//		GameGUI.repaintAllButtons();
	}

}
