package controler;


import model.GameModelInterface;
import tools.*;
import view.GameGUIInterface;

import javax.swing.*;
import java.util.Map;

public class ControllerLocal implements ControllerModelViewInterface {

    private GameModelInterface gameModel;
    private GameGUIInterface gameGUI;

    /**
     * __CONSTRUCTOR__
     * @param gameModel
     * @param gameGUI
     */
    public ControllerLocal(GameModelInterface gameModel, GameGUIInterface gameGUI) {
        System.out.println("Controller\n");
        this.gameModel = gameModel;
        this.gameGUI = gameGUI;// set latter
        initGame();
    }

    /**
     *
     * @param x
     * @param y
     */
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

    /**
     *
     * @param xDest
     * @param yDest
     */
    public void moveBoat(int xDest, int yDest){
        ProcessedPosition processedPosition = this.gameModel.moveBoat(xDest, yDest);
        if(processedPosition != null){
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.setProcessedPotion(processedPosition);
//            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords((Player) this.gameModel.getPlayers().get(0)));
            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
        }else{
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *
     */
    public void rotateBoatClockWise(){
        ProcessedPosition processedPosition = this.gameModel.rotateBoatClockWise();
        if(processedPosition != null){
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.setProcessedPotion(processedPosition);
//            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords((Player) this.gameModel.getPlayers().get(0)));
            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
        }else{
        this.gameGUI.setCurrentAction(ActionType.SELECT);
            JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *
     */
    public void rotateBoatCounterClockWise(){
        ProcessedPosition processedPosition = this.gameModel.rotateBoatCounterClockWise();
        if(processedPosition != null){
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.setProcessedPotion(processedPosition);
//            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords((Player) this.gameModel.getPlayers().get(0)));
            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
        }else{
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     *
     * @param x
     * @param y
     */
	@Override
	public void shoot(int x, int y) {
        Pair<ResultShoot, ProcessedPosition> result = this.gameModel.shoot(new Coord(x, y));
        if(result != null){
            if(result.getFirst() != null){
                this.gameGUI.setProcessedPotion(result.getSecond());
            }
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.message("shoot result : "+result.getFirst());
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
        }else{
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
        }
	}

    /**
     *
     */
    public void initGame() {
        // TODO Auto-generated method stub
        System.out.println("test print list of boat");
        System.out.println(this.gameModel.getListOfBoat());
        Map<Integer,ProcessedPosition> initBoatPos = this.gameModel.getListOfBoat();
        this.gameGUI.initGame(initBoatPos);
        this.gameGUI.setCurrentAction(ActionType.SELECT);
//        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords((Player) this.gameModel.getPlayers().get(0)));
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
        this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
    }

    /**
     *
     * @param actionType
     */
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
}
