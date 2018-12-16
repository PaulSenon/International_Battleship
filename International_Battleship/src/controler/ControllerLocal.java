package controler;

import model.GameModelInterface;
import tools.*;
import view.GameGUIInterface;

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
            this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoordsCurrentPlayer());
            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
            this.gameModel.unselectBoat();
            this.gameGUI.setSelectedBoat(null);
        }else{
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.messagePopUp("Un bateau doit être sélectionné.");
        }
    }

    /**
     *
     */
    public void rotateBoatClockWise(){
        ProcessedPosition processedPosition = this.gameModel.rotateBoatClockWise();
        if(processedPosition != null && this.gameGUI.boatIsSelected()){
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.setProcessedPotion(processedPosition);
            this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoordsCurrentPlayer());
            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
            this.gameModel.unselectBoat();
            this.gameGUI.setSelectedBoat(null);
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
        if(processedPosition != null && this.gameGUI.boatIsSelected()){
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.setProcessedPotion(processedPosition);
            this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoordsCurrentPlayer());
            this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
            this.gameModel.unselectBoat();
            this.gameGUI.setSelectedBoat(null);
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
        Coord target = new Coord(x, y);
        Pair<ResultShoot, ProcessedPosition> result = this.gameModel.shoot(target);
        if(result != null && this.gameGUI.boatIsSelected()) {
            if (result.getFirst() != null) {
                if (result.getSecond() != null) {this.gameGUI.setProcessedPotion(result.getSecond());                }
                this.gameGUI.displayResult(result.getFirst(), target);
                this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
            }
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.message("shoot result : "+result.getFirst());
            this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
            this.gameModel.unselectBoat();
            this.gameGUI.setSelectedBoat(null);
        } else{
            this.gameGUI.setCurrentAction(ActionType.SELECT);
            this.gameGUI.messagePopUp("Un bateau doit être sélectionné.");
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
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
        this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoordsCurrentPlayer());
        this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
    }

    /**
     *
     * @param actionType
     */
    public void requestActionType(ActionType actionType){
        if(this.gameModel.hasSelectedBoat()){
            this.gameGUI.setCurrentAction(actionType);
        }else{
            this.gameGUI.messagePopUp("Un bateau doit être sélectionné.");
        }
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
		this.gameModel.EndActionsOfPlayer();
		this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
		this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer());
		this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoordsCurrentPlayer());
	}
}
