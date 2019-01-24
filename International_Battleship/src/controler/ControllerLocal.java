package controler;

import model.GameModelInterface;
import model.exceptions.SelectBoatException;
import tools.*;
import view.ButtonType;
import view.GameGUIInterface;

import java.util.List;
import java.util.Map;

public class ControllerLocal implements ControllerModelViewInterface {

    protected GameModelInterface gameModel;
    protected GameGUIInterface gameGUI;

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
        if(this.tryToSelectBoat(x, y)) {
            this.gameGUI.setCurrentAction(ActionType.DEFAULT());
        }else{
            this.gameGUI.message("Il n'y a pas un de vos bateaux ici.");
        }
	}

    /**
     * Try to perform boat selection,
     * true if successful
     * false if it failed
     * @param x
     * @param y
     * @return isBoatHasBeenSelected
     */
	private boolean tryToSelectBoat(int x, int y){
        try{
            ProcessedPosition processedPosition = this.gameModel.selectBoat(x, y);
            this.gameGUI.setSelectedBoat(processedPosition);
            this.updateControls();
            return true;
        }catch (SelectBoatException e){
            return false;
        }
    }



    /**
     *
     * @param xDest
     * @param yDest
     */
    public void moveBoat(int xDest, int yDest){
        // Try to select boat first
        if(this.tryToSelectBoat(xDest, yDest)) return;
        // Do move action only if select failed...

        // else, perform move action
        ProcessedPosition processedPosition = this.gameModel.moveBoat(xDest, yDest);
        if(processedPosition != null){
            // Update position
            this.sendProcessedPosition(processedPosition);
            // Do the usual updates
            this.routineUpdates();
        }else{
            this.gameGUI.messagePopUp("You must select a boat to perform this action");
        }
    }

    /**
     *
     */
    public void rotateBoatClockWise(){
        // perform rotate action
        ProcessedPosition processedPosition = this.gameModel.rotateBoatClockWise();
        if(processedPosition != null){
            // Update position
            this.sendProcessedPosition(processedPosition);
            // Do the usual updates
            this.routineUpdates();
        }else{
            this.gameGUI.messagePopUp("You must select a boat to perform this action");
        }

    }

    /**
     *
     */
    public void rotateBoatCounterClockWise(){
        // perform rotate action
        ProcessedPosition processedPosition = this.gameModel.rotateBoatCounterClockWise();
        if(processedPosition != null){
            // Update position
            this.sendProcessedPosition(processedPosition);
            // Do the usual updates
            this.routineUpdates();
        }else{
            this.gameGUI.messagePopUp("You must select a boat to perform this action");
        }
    }

    /**
     *
     * @param x
     * @param y
     */
	@Override
	public void shoot(int x, int y) {
        // Try to select boat
        if(this.tryToSelectBoat(x, y)){
            this.gameGUI.setCurrentAction(ActionType.DEFAULT()); // SHOOT => DEFAULT
            return;
        }
        // Perform shoot action only if select boat failed

        Coord target = new Coord(x, y);
        ProcessedPosition result = this.gameModel.shoot(target);

        // update visual
        ProcessedPosition processedPosition = result;
        // update if needed
        if(processedPosition != null){
            this.sendProcessedPosition(processedPosition);
        }

        // Do the usual updates
        this.routineUpdates();
	}

    @Override
    public void specialAction(int x, int y) {
        // Try to select boat
        if(tryToSelectBoat(x, y)){
            this.gameGUI.setCurrentAction(ActionType.DEFAULT()); // SPECIALACTION => MOVE
            return;
        }
        // Perform shoot action only if select boat failed

        Coord target = new Coord(x, y);
        List<ProcessedPosition> results = this.gameModel.specialAction(target);

        if(results != null){
            for(ProcessedPosition processedPosition : results){
                this.sendProcessedPosition(processedPosition);
            }
        }

        this.routineUpdates();
    }

    /**
     * It send a processedPosition to classes that need update
     * (GUI / Network / ...)
     * @param processedPosition
     */
    // TODO move this in an abstract parent and implement it differently for Local and Client controller
	protected void sendProcessedPosition(ProcessedPosition processedPosition) {
        this.gameGUI.setProcessedPosition(processedPosition);
    }
	
    /**
     * It send a processedProps to classes that need update
     * (GUI / Network / ...)
     * @param processedProps
     */
    // TODO move this in an abstract parent and implement it differently for Local and Client controller
	protected void sendProcessedProps(List<ProcessedProps> processedProps) {
        this.gameGUI.setProcessedProps(processedProps);
    }

    protected void routineUpdates(){
        // update visible area
        //Send processedProps to multi
        this.sendProcessedProps(this.gameModel.getProcessedPropsToUpdate());
        // on affiche les zones visibles
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer(), this.gameModel.getPortsCoordsCurrentPlayer());
        // on affiche les bateau dans les zones visibles
        this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoordsCurrentPlayer());
        //on affiche les mines dans les zones visibles
        this.gameGUI.setVisibleMines(this.gameModel.getVisibleCoordsCurrentPlayer());
        // update action points
        this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());

        // update controls
        this.updateControls();
    }

    // TODO
    protected void updateControls(){
	    // get if there is a selected boat => else disable all buttons but end of turn
        if(gameModel.hasSelectedBoat()){
            boolean canShoot = gameModel.canCurrentBoatShoot();
            gameGUI.setButtonEnabled(ButtonType.SHOOT, canShoot);

            boolean canRotate = gameModel.canCurrentBoatRotate();
            gameGUI.setButtonEnabled(ButtonType.ROTATECW, canRotate);
            gameGUI.setButtonEnabled(ButtonType.ROTATECCW, canRotate);

            boolean canSpecialAction = gameModel.canCurrentBoatDoSpecialAction();
            gameGUI.setButtonEnabled(ButtonType.SPECIALACTION, canSpecialAction);

            if(!canShoot && !canRotate && !canSpecialAction){
                gameGUI.setButtonHighLight(ButtonType.ENDTURN, true);
            }else{
                gameGUI.setButtonHighLight(ButtonType.ENDTURN, false);
            }
        }else{
            gameGUI.setButtonEnabled(ButtonType.SHOOT, false);
            gameGUI.setButtonEnabled(ButtonType.ROTATECCW, false);
            gameGUI.setButtonEnabled(ButtonType.ROTATECW, false);
            gameGUI.setButtonEnabled(ButtonType.SPECIALACTION, false);
            gameGUI.setButtonHighLight(ButtonType.ENDTURN, false);
        }
        gameGUI.setButtonEnabled(ButtonType.ENDTURN, true);
    }

    /**
     *
     */
    public void initGame() {
        // DEBUG
        System.out.println("test print list of boat");
        System.out.println(this.gameModel.getListOfBoat());

        // Init boats on board
        Map<Integer,ProcessedPosition> initBoatPos = this.gameModel.getListOfBoat();
        Map<Integer, Integer> boatsRelatedToPlayers = this.gameModel.getBoatsAndPlayersId();
        List<ProcessedProps> processedProps = this.gameModel.getProcessedPropsToUpdate();

        this.gameGUI.initGame(initBoatPos, boatsRelatedToPlayers, processedProps);
        // Init currentAction
        this.gameGUI.setCurrentAction(ActionType.INIT());
        // Init visible area
        this.gameGUI.setVisibleCoord(this.gameModel.getVisibleCoordsCurrentPlayer(), this.gameModel.getPortsCoordsCurrentPlayer());
        // Init visible boats (fragment of enemy boats inside the visible area)
        //      => note: it's useless here but keep it for consistency.
        this.gameGUI.setVisibleBoats(this.gameModel.getVisibleCoordsCurrentPlayer());
        //Init visible boats
        this.gameGUI.setVisibleMines(this.gameModel.getVisibleCoordsCurrentPlayer());
        // Init action points
        this.gameGUI.setNbAP(this.gameModel.getApCurrentPlayer());

        this.updateControls();
    }

    /**
     * Used from GUI to request a new action (use from buttons listeners)
     * @param actionType
     */
    public void requestActionType(ActionType actionType){
        if(this.gameModel.hasSelectedBoat()){
            if(actionType == ActionType.SPECIAL && this.gameModel.isInstantActionForCurrentBoat()){
                this.specialAction(-1,-1);
                return;
            }
            this.gameGUI.setCurrentAction(actionType);
        }else{
            this.gameGUI.messagePopUp("Un bateau doit être sélectionné.");
        }
    }

	@Override
	public void EndActionsOfPlayer() {
        // end turn
		this.gameModel.EndActionsOfPlayer();
        // unselect boat
		this.gameModel.unselectBoat();
        this.gameGUI.setSelectedBoat(null);
        // reset action
        this.gameGUI.setCurrentAction(ActionType.INIT());
        // update controls
        this.routineUpdates();
	}

}
