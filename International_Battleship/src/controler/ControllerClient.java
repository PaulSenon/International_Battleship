package controler;


import model.GameModelInterface;
import multiplayer.Client;
import tools.*;
import view.GameGUIInterface;

import javax.swing.*;
import java.util.Map;

public class ControllerClient implements ControllerModelViewInterface {

    private GameModelInterface gameModel;
    private GameGUIInterface gameGUI;
    private Client client;



    /**
     * __CONSTRUCTOR__
     * @param gameModel
     * @param gameGUI
     */
    public ControllerClient(GameModelInterface gameModel, GameGUIInterface gameGUI,Client client) {
        System.out.println("Controller\n");
        this.gameModel = gameModel;
        this.gameGUI = gameGUI;// set latter
        this.client = client;
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

        if(!this.gameModel.itsTurn())return;
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
            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
            this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
            this.client.sendProcessedPosition(processedPosition);//Send proc pos to network
        }else{
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void update(ProcessedPosition processedPosition){
        this.gameGUI.setProcessedPotion(processedPosition);
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        this.gameModel.setProcessedPosition(processedPosition);
    }

    /**
     *
     */
    public void rotateBoatClockWise(){
        ProcessedPosition processedPosition = this.gameModel.rotateBoatClockWise();
        if(processedPosition != null){
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.setProcessedPotion(processedPosition);
            this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
            this.gameModel.unselectBoat();
            this.gameGUI.setSelectedBoat(null);
            this.client.sendProcessedPosition(processedPosition);//Send proc pos to network
        }else{
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.messagePopUp("Un bateau doit être sélectionné.");
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
            this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
            this.gameModel.unselectBoat();
            this.gameGUI.setSelectedBoat(null);
            this.client.sendProcessedPosition(processedPosition);//Send proc pos to network
        }else{
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.messagePopUp("Un bateau doit être sélectionné.");
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
            this.client.sendProcessedPosition(result.getSecond());//Send proc pos to network
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
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
        this.gameGUI.setControlsEnable(this.gameModel.itsTurn());
    }

    /**
     *
     * @param actionType
     */
    public void requestActionType(ActionType actionType){
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
        this.gameModel.specialAction(coordSquare);
        this.gameGUI.setCurrentAction(ActionType.MOVE);
        this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
    }

	@Override
	public void EndActionsOfPlayer() {
        this.setupEndTurn();
        this.client.endOfTurn();

	}

    public void setupEndTurn() {
        this.gameModel.EndActionsOfPlayer();
        this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        this.gameModel.unselectBoat();
        this.gameGUI.setSelectedBoat(null);
        this.gameGUI.setControlsEnable(this.gameModel.itsTurn());
    }
}