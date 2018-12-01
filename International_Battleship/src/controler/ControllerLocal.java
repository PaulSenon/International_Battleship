package controler;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import model.BoatName;
import model.GameModelInterface;
import model.ResultSpecialAction;
import tools.ActionType;
import tools.Coord;
import tools.ProcessedPosition;
import view.ButtonType;
import view.GameGUIInterface;

import javax.swing.*;

import java.util.Map;

@objid ("876e9f9a-d77c-4a9d-809e-0854b8d95d55")
public class ControllerLocal implements ControllerModelViewInterface {

    private GameModelInterface gameModel;
    private GameGUIInterface gameGUI;

    @objid ("be0371df-d97b-409c-a49c-c194011d27a8")
    public ControllerLocal(GameModelInterface gameModel, GameGUIInterface gameGUI) {
        System.out.println("Controller\n");
        this.gameModel = gameModel;
        this.gameGUI = gameGUI; // set latter
        initGame();
    }

	public void selectBoat(int x, int y) {
//		if(this.gameModel.selectBoat(x, y)){
//			System.out.println("YES boat has been selected");//pour debug mais je laisse tant que la vue n'est géré
//		    this.gameGUI.setCurrentAction(ActionType.MOVE);//on change la valeur du ActionType pour la return après
//            this.gameGUI.setSelectedBoatByCoord(x,y);
//        }
        ProcessedPosition processedPosition = this.gameModel.selectBoat(x, y);
        if(processedPosition != null){
            this.gameGUI.setCurrentAction(ActionType.MOVE);
            this.gameGUI.setSelectedBoat(processedPosition);
        }else{
            // TODO just a placeholder yet
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            System.out.println("SelectBoatFailed");
        }
	}

    public void moveBoat(int xDest, int yDest){
        ProcessedPosition processedPosition = this.gameModel.moveBoat(xDest, yDest);
        if(processedPosition != null){
            this.gameGUI.setProcessedPotion(processedPosition);
            this.gameGUI.setCurrentAction(ActionType.SELECT);
        }else{
            this.gameGUI.setCurrentAction(ActionType.MOVE);
            JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void rotateBoatClockWise(){
        // TODO move direction dans tools
        ProcessedPosition processedPosition = this.gameModel.rotateBoatClockWise();
        if(processedPosition != null){
            this.gameGUI.setProcessedPotion(processedPosition);
        }else{
            JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
        }
        this.gameGUI.setCurrentAction(ActionType.SELECT);
    }

    public void rotateBoatCounterClockWise(){
        // TODO move direction dans tools
        ProcessedPosition processedPosition = this.gameModel.rotateBoatCounterClockWise();
        if(processedPosition != null){
            this.gameGUI.setProcessedPotion(processedPosition);
        }else{
            JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
        }
        this.gameGUI.setCurrentAction(ActionType.SELECT);
    }

	@Override
	public void shoot(int x, int y) {
        System.out.println("Controller.shoot not yet implemented");
        this.gameGUI.setCurrentAction(ActionType.SELECT);
//		Coord target = squareSelected.getCoord();

            // TODO FIX#46 : CHANGE THIS CO COMMUNICATE WITH GLOBALE GUI INSTEAD OF GRIDGUI
            //this.gameGUI.messageToUser(this.battleShipGame.shoot(target));

//		GameGUI.repaintAllButtons();
	}

    public void initGame() {
        // TODO Auto-generated method stub
        System.out.println("test print list of boat");
        System.out.println(this.gameModel.getListOfBoat());
        Map<BoatName,ProcessedPosition> initBoatPos = this.gameModel.getListOfBoat();
        this.gameGUI.initGame(initBoatPos);
        this.gameGUI.setCurrentAction(ActionType.SELECT);
    }

    public void requestActioType(ActionType actionType){
        this.gameGUI.setCurrentAction(actionType);
    }

    /**
     * FOR DEBUG
     * @return GameGUIInterface
     */
    public GameGUIInterface getGameGUI() {
        return gameGUI;
    }

	@Override
	public void specialAction(Coord coordSquare) {
		ResultSpecialAction ret = this.gameModel.specialAction(coordSquare);
		this.gameGUI.setCurrentAction(ActionType.MOVE);
		switch(ret){
			case FAIL:
				JOptionPane.showMessageDialog(null, "L'action spécial n'a pas pu être réalisé correctement.", null , JOptionPane.INFORMATION_MESSAGE);
				break;
			case TARGET:
				JOptionPane.showMessageDialog(null, "L'action spéciale ciblé a été réussi avec succès.", null , JOptionPane.INFORMATION_MESSAGE);
				break;
			case INSTANT:
				JOptionPane.showMessageDialog(null, "L'action spéciale instantané a été réussi avec succès.", null , JOptionPane.INFORMATION_MESSAGE);
				break;
		}
	}
}
