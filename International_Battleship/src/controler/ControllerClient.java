package controler;


import model.GameModelInterface;
import multiplayer.Client;
import tools.ActionType;
import tools.ProcessedPosition;
import view.GameGUIInterface;

import java.util.Map;

public class ControllerClient extends ControllerLocal{

    private Client client;

    /**
     * __CONSTRUCTOR__
     * @param gameModel
     * @param gameGUI
     */
    public ControllerClient(GameModelInterface gameModel, GameGUIInterface gameGUI,Client client) {
        super(gameModel, gameGUI);
        System.out.println("Controller\n");
        this.client = client;
        initGame();
    }

    public void update(ProcessedPosition processedPosition){
        this.gameGUI.setProcessedPosition(processedPosition);
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        this.gameModel.setProcessedPosition(processedPosition);
    }

    @Override
    public void specialAction(int x, int y) {
        super.specialAction(x, y);
        // todo sync client
    }

	@Override
	public void EndActionsOfPlayer() {
        super.EndActionsOfPlayer();
        this.client.endOfTurn();
        this.gameGUI.setControlsEnabled(this.gameModel.itsTurn());
	}

	public void setupEndTurn(){
        super.EndActionsOfPlayer();
        this.updateControls();
    }

    @Override
    protected void sendProcessedPosition(ProcessedPosition processedPosition) {
        super.sendProcessedPosition(processedPosition);
        this.client.sendProcessedPosition(processedPosition);
    }

    @Override
    protected void routineUpdates(){
        // update visible area
        // on affiche les zones visibles
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        // on affiche les bateau dans les zones visibles
        this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        // update action points
        this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());
        // update controls
        this.updateControls();
    }

    public void initGame() {
        // DEBUG
        System.out.println("test print list of boat");
        System.out.println(this.gameModel.getListOfBoat());

        // Init boats on board
        Map<Integer,ProcessedPosition> initBoatPos = this.gameModel.getListOfBoat();
        this.gameGUI.initGame(initBoatPos);
        // Init currentAction
        this.gameGUI.setCurrentAction(ActionType.INIT());
        // Init visible area
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        // on affiche les bateau dans les zones visibles
        this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoords(this.gameModel.getClientPlayer()));
        // Init action points
        this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());

        if(this.gameModel.itsTurn()){
            this.updateControls();
        }else {
            this.gameGUI.setControlsEnabled(false);
        }

    }
}